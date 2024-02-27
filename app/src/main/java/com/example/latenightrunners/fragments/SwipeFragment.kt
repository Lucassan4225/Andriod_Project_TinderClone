package com.example.latenightrunners.fragments

import DatingAdapter
import FirestoreUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.latenightrunners.databinding.SwipeFragmentBinding
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class SwipeFragment : Fragment() {
    private lateinit var binding: SwipeFragmentBinding
    private lateinit var manager: CardStackLayoutManager
    private lateinit var adapter: DatingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = SwipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
    }

    private fun init() {
        manager = CardStackLayoutManager(requireContext(), object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {}

            override fun onCardSwiped(direction: Direction?) {
                if (manager.topPosition == adapter.itemCount) {
                    Toast.makeText(requireContext(), "This is the last card", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCardRewound() {}

            override fun onCardCanceled() {}

            override fun onCardAppeared(view: View?, position: Int) {}

            override fun onCardDisappeared(view: View?, position: Int) {}
        })

        manager.setVisibleCount(3)
        manager.setTranslationInterval(0.6f)
        manager.setScaleInterval(0.8f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)

        binding.cardStackView.layoutManager = manager
        binding.cardStackView.itemAnimator = DefaultItemAnimator()
    }

    private fun getData() {
        FirestoreUtil.db.collection("users")
            .get()
            .addOnSuccessListener { userDocuments ->
                val userList = ArrayList<QueryDocumentSnapshot>()

                for (userDocument in userDocuments) {
                    userList.add(userDocument)
                }

                FirestoreUtil.db.collection("images")
                    .get()
                    .addOnSuccessListener { imageDocuments ->
                        val imageMap = HashMap<String, String>()

                        for (imageDocument in imageDocuments) {
                            val userId = imageDocument.id // Use document ID instead of getString("userId")
                            val imageUrl = imageDocument.getString("image_url")

                            if (userId != null && imageUrl != null) {
                                imageMap[userId] = imageUrl
                            }
                        }

                        // Pass both user and image data to the adapter
                        adapter = DatingAdapter(requireContext(), userList, imageMap)
                        binding.cardStackView.adapter = adapter
                    }
                    .addOnFailureListener { exception ->
                        Log.e("SwipeFragment", "Error getting images: ", exception)
                        Toast.makeText(requireContext(), "Failed to retrieve image data", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("SwipeFragment", "Error getting users: ", exception)
                Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show()
            }

    }

}


