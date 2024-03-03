
package com.example.latenightrunners.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.latenightrunners.adapter.MatchAdapter
import com.example.latenightrunners.data.User
import com.example.latenightrunners.databinding.MatchFragmentBinding
import com.example.latenightrunners.firestore.FirestoreUtil
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
class MatchFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MatchAdapter
    private var _binding: MatchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MatchFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        userArrayList = ArrayList()
        myAdapter = MatchAdapter(userArrayList)
        recyclerView.adapter = myAdapter



        fetchMatchedUsers()


        return view
    }

    private fun fetchMatchedUsers() {
        FirestoreUtil.getMatchedUsersImages(
            onSuccess = { userIds ->
                loadMatchedUsersImages(userIds)
            },
            onFailure = { exception ->

            }
        )
    }

    private fun loadMatchedUsersImages(userIds: List<String>) {
        FirestoreUtil.getImagesForUserIds(
            userIds = userIds,
            onSuccess = { imageUrls ->
                val matchedUsers = imageUrls.map { User(it) }
                userArrayList.addAll(matchedUsers)
                myAdapter.notifyDataSetChanged()
            },
            onFailure = { exception ->
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}