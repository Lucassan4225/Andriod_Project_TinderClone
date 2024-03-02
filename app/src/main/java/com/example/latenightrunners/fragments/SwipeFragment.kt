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
                    // Get the current user's ID
                    val userId = FirestoreUtil.getUserId()

                    // Update user's matching status based on swipe direction
                    if (direction == Direction.Right) {
                        FirestoreUtil.updateUserMatchingStatus(userId, true,
                            onSuccess = {
                                // Add the image of the swiped user to the MatchFragment
//                                addImageToMatchFragment(userId)
                            },
                            onFailure = { exception ->
                                // Handle failure
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
                    FirestoreUtil.db.collection("users") // Exclude current user
                        .whereEqualTo("gender", interestedGender)
                        .whereGreaterThanOrEqualTo("age", minAgePre) // Filter users with age greater than or equal to minAgePre
                        .whereLessThanOrEqualTo("age", maxAgePre) // Filter users with age less than or equal to maxAgePre
                } else {
                    FirestoreUtil.db.collection("users")// Exclude current user
                        .whereGreaterThanOrEqualTo("age", minAgePre) // Filter users with age greater than or equal to minAgePre
                        .whereLessThanOrEqualTo("age", maxAgePre) // Filter users with age less than or equal to maxAgePre
                }

                usersCollection.get()
                    .addOnSuccessListener { userDocuments ->
                        val userList = ArrayList<QueryDocumentSnapshot>()
                        for (userDocument in userDocuments) {
                            userList.add(userDocument)
                        }

                        // Fetch image documents separately
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

                                // Set up the adapter with fetched data
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
//    private fun fetchUsers(interestedGender: String?) {
//        val currentUserId = FirestoreUtil.getUserId()
//
//        // Retrieve user age preferences
//        FirestoreUtil.getUserAgePreferences(currentUserId,
//            onSuccess = { minAgePre, maxAgePre ->
//                val usersCollection = if (interestedGender != null && (interestedGender == "Men" || interestedGender == "Women")) {
//                    FirestoreUtil.db.collection("users")
//                        .whereEqualTo("gender", interestedGender)
//                        .whereGreaterThanOrEqualTo("age", minAgePre)
//                        .whereLessThanOrEqualTo("age", maxAgePre)
//                        .whereNotEqualTo("userId", currentUserId) // Exclude current user
//                } else {
//                    FirestoreUtil.db.collection("users")
//                        .whereGreaterThanOrEqualTo("age", minAgePre)
//                        .whereLessThanOrEqualTo("age", maxAgePre)
//                        .whereNotEqualTo("userId", currentUserId) // Exclude current user
//                }
//
//                // Use batched queries for improved performance
//                val batchedList = mutableListOf<DocumentReference>()
//                var batchSize = 10 // Adjust the batch size as needed
//                var count = 0
//
//                usersCollection.get()
//                    .addOnSuccessListener { userDocuments ->
//                        for (document in userDocuments) {
//                            count++
//                            batchedList.add(document.reference)
//                            if (count == batchSize) {
//                                fetchBatchedUsers(batchedList)
//                                batchedList.clear()
//                                count = 0
//                            }
//                        }
//
//                        // Process any remaining documents
//                        if (count > 0) {
//                            fetchBatchedUsers(batchedList)
//                        }
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.e("SwipeFragment", "Error getting users: ", exception)
//                        Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show()
//                    }
//            },
//            onFailure = { exception ->
//                Log.e("SwipeFragment", "Error getting age preferences: ", exception)
//                Toast.makeText(requireContext(), "Failed to retrieve age preferences", Toast.LENGTH_SHORT).show()
//            }
//        )
//    }
//    private fun fetchBatchedUsers(batchedList: List<DocumentReference>) {
//        // Fetch batched users
//        val batchTasks = mutableListOf<Task<DocumentSnapshot>>()
//        for (documentRef in batchedList) {
//            batchTasks.add(documentRef.get())
//        }
//
//        Tasks.whenAllSuccess<DocumentSnapshot>(batchTasks)
//            .addOnSuccessListener { batchResults ->
//                // Process batched results
//                val userList = ArrayList<QueryDocumentSnapshot>()
//                for (snapshot in batchResults) {
//                    if (snapshot.exists()) {
//                        userList.add(snapshot as QueryDocumentSnapshot)
//                    }
//                }
//
//                val imageTasks = mutableListOf<Task<DocumentSnapshot>>()
//                for (userSnapshot in userList) {
//                    val userId = userSnapshot.id
//                    val imageRef = FirestoreUtil.db.collection("images").document(userId)
//                    imageTasks.add(imageRef.get())
//                }
//
//                Tasks.whenAllSuccess<DocumentSnapshot>(imageTasks)
//                    .addOnSuccessListener { imageResults ->
//                        val imageMap = HashMap<String, String>()
//                        for ((index, imageSnapshot) in imageResults.withIndex()) {
//                            val userId = userList[index].id
//                            val imageUrl = imageSnapshot.getString("image_url")
//                            if (imageUrl != null) {
//                                imageMap[userId] = imageUrl
//                            }
//                        }
//
//                        // Create the adapter and populate the UI with users and their images
//                        adapter = DatingAdapter(requireContext(), userList, imageMap)
//                        binding.cardStackView.adapter = adapter
//                    }
//                    .addOnFailureListener { exception ->
//                        Log.e("SwipeFragment", "Error getting images: ", exception)
//                        Toast.makeText(requireContext(), "Failed to retrieve image data", Toast.LENGTH_SHORT).show()
//                    }
//                // Continue with processing userList and creating adapters
//                // Note: This part is similar to the existing logic
//
//            }
//            .addOnFailureListener { exception ->
//                Log.e("SwipeFragment", "Error getting batched users: ", exception)
//                Toast.makeText(requireContext(), "Failed to retrieve user data", Toast.LENGTH_SHORT).show()
//            }
//    }


}
