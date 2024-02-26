package com.example.latenightrunners.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.latenightrunners.adapter.DatingAdapter
import com.example.latenightrunners.databinding.SwipeFragmentBinding
import com.example.latenightrunners.firestore.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class SwipeFragment: Fragment() {
    private lateinit var binding: SwipeFragmentBinding
    private lateinit var manager: CardStackLayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?

    ): View {

        binding = SwipeFragmentBinding.inflate(layoutInflater)



        getData()

        return binding.root


    }

    private fun init() {
        manager = CardStackLayoutManager(requireContext(), object: CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {

            }

            override fun onCardSwiped(direction: Direction?) {

                if (manager!!.topPosition == list.size){
                    Toast.makeText(requireContext(),"This is last card",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCardRewound() {

            }

            override fun onCardCanceled() {

            }

            override fun onCardAppeared(view: View?, position: Int) {

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }

        })

        manager.setVisibleCount(3)
        manager.setTranslationInterval(0.6f)
        manager.setScaleInterval(0.8f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
    }

    private lateinit var list : ArrayList<UserModel>
    private fun getData() {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("users")
            .get()
            .addOnSuccessListener { documents ->
                list = ArrayList()
                for (document in documents) {
                    val userModel = document.toObject(UserModel::class.java)
                    list.add(userModel)
                }
                init()

                binding.cardStackView.layoutManager = manager
                binding.cardStackView.itemAnimator = DefaultItemAnimator()
                binding.cardStackView.adapter = DatingAdapter(requireContext(), list)
            }
            .addOnFailureListener { exception ->
                Log.e("SwipeFragment", "Error getting documents: ", exception)
                Toast.makeText(requireContext(), "Failed to retrieve data", Toast.LENGTH_SHORT).show()
            }
    }



}