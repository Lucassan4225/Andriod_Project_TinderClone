package com.example.latenightrunners.fragments
import com.example.latenightrunners.adapter.DatingAdapter
import com.example.latenightrunners.firestore.FirestoreUtil
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.latenightrunners.AddInfoActivity
import com.example.latenightrunners.DiscoverSettingsActivity
import com.example.latenightrunners.databinding.SwipeFragmentBinding
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
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
        getData()
        init()
    }
    fun onUpButtonClick(position: Int) {

        val intent = Intent(requireContext(), AddInfoActivity::class.java)
        startActivity(intent)
    }

    fun onConfigurationIconClicked(view: View) {
        val intent = Intent(requireContext(), DiscoverSettingsActivity::class.java)
        startActivity(intent)
    }
    private fun init() {
        manager = CardStackLayoutManager(requireContext(), object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {}
            override fun onCardSwiped(direction: Direction?) {
                if (manager.topPosition == adapter.itemCount) {
                    Toast.makeText(requireContext(), "This is the last card", Toast.LENGTH_SHORT).show()
                } else {

                    val userId = FirestoreUtil.getUserId()

                    if (direction == Direction.Right) {
                        FirestoreUtil.updateUserMatchingStatus(userId, true,
                            onSuccess = {

                            },
                            onFailure = { exception ->

                                Log.e("com.example.latenightrunners.fragments.SwipeFragment", "Error updating user matching status", exception)
                            }
                        )
                    } else if (direction == Direction.Left) {
                        FirestoreUtil.updateUserMatchingStatus(userId, false,
                            onSuccess = {

                            },
                            onFailure = { exception ->

                                Log.e("com.example.latenightrunners.fragments.SwipeFragment", "Error updating user matching status", exception)
                            }
                        )

                    }
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
        binding.iconConfiguration.setOnClickListener {
            onConfigurationIconClicked(it)
        }
    }
    private fun getData() {
        FirestoreUtil.getCurrentUserInterestedGender(
            onSuccess = { interestedGender ->
                fetchUsers(interestedGender)
            },
            onFailure = { exception ->
                Log.e("com.example.latenightrunners.fragments.SwipeFragment", "Error getting interested gender: ", exception)
                Toast.makeText(requireContext(), "Failed to retrieve interested gender", Toast.LENGTH_SHORT).show()
            }
        )
    }
    private fun fetchUsers(interestedGender: String?) {
        val currentUserId = FirestoreUtil.getUserId()
// Retrieve user age preferences
        FirestoreUtil.getUserAgePreferences(currentUserId,
            onSuccess = { minAgePre, maxAgePre ->
                val usersCollection = if (interestedGender != null && (interestedGender == "Men" || interestedGender == "Women")) {
                    FirestoreUtil.db.collection("users")
                        .whereEqualTo("gender", interestedGender)
                        .whereGreaterThanOrEqualTo("age", minAgePre)
                        .whereLessThanOrEqualTo("age", maxAgePre)
                } else {
                    FirestoreUtil.db.collection("users")
                        .whereGreaterThanOrEqualTo("age", minAgePre)
                        .whereLessThanOrEqualTo("age", maxAgePre)
                }
                usersCollection.get()
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
                                    val userId = imageDocument.id
                                    val imageUrl = imageDocument.getString("image_url")
                                    if (userId != null && imageUrl != null) {
                                        imageMap[userId] = imageUrl
                                    }
                                }

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
            },
            onFailure = { exception ->
                Log.e("SwipeFragment", "Error getting age preferences: ", exception)
                Toast.makeText(requireContext(), "Failed to retrieve age preferences", Toast.LENGTH_SHORT).show()
            }
        )
    }

}
