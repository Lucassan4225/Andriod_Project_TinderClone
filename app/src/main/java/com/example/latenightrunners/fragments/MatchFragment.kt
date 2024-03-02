package com.example.latenightrunners.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latenightrunners.adapter.MatchAdapter
import com.example.latenightrunners.databinding.MatchFragmentBinding
import com.example.latenightrunners.firestore.FirestoreUtil

class MatchFragment : Fragment() {
    private lateinit var binding: MatchFragmentBinding
    private lateinit var adapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = MatchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize RecyclerView and adapter
        adapter = MatchAdapter(requireContext(), mutableListOf())
        binding.rvMatched.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMatched.adapter = adapter

        // Fetch matched users and their images
        fetchMatchedUsersAndImages()
    }

    private fun fetchMatchedUsersAndImages() {
        FirestoreUtil.getMatchedUsers(
            onSuccess = { matchedUsers ->
                val userIds = matchedUsers.mapNotNull { it["userId"] as? String }
                fetchUserImages(userIds)
            },
            onFailure = { exception ->
                Log.e("com.example.latenightrunners.fragments.MatchFragment", "Error getting matched users: ", exception)
            }
        )
    }

    private fun fetchUserImages(userIds: List<String>) {
        val imageList = mutableListOf<String>()

        userIds.forEach { userId ->
            FirestoreUtil.getProfileImageUri(
                userId,
                onSuccess = { imageUrl ->
                    imageUrl?.let {
                        imageList.add(it)
                        adapter.setImageList(imageList)
                    }
                },
                onFailure = { exception ->
                    Log.e("MatchFragment", "Error getting profile image URI for user $userId: ", exception)
                }
            )
        }
    }
}