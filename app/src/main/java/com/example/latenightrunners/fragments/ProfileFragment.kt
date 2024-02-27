//package com.example.latenightrunners.fragments
//
//import android.net.Uri
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.fragment.app.Fragment
//import com.example.latenightrunners.databinding.ProfileFragmentBinding
//
//class ProfileFragment: Fragment() {
//    private lateinit var view: ProfileFragmentBinding
//    private var imageUri: Uri? = null
//    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
//        imageUri = it
//
//        view.civProfile.setImageURI(imageUri)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        view = ProfileFragmentBinding.inflate(inflater,container,false)
//        return view.root
////
////        view.civProfile.setOnClickListener {
////            selectImage.launch("image/*")
////        }
////
////        view.   .setOnClickListener{
////
////        }
//    }
//}
package com.example.latenightrunners.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.latenightrunners.EditProfileActivity
import com.example.latenightrunners.databinding.ProfileFragmentBinding
import com.example.latenightrunners.firestore.FirestoreUtil
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
    private lateinit var view: ProfileFragmentBinding
    private var imageUri: Uri? = null
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
        view.civProfile.setImageURI(imageUri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = ProfileFragmentBinding.inflate(inflater, container, false)

        // Retrieve user's image from Firestore and load it into civProfile
        val userId = FirestoreUtil.getUserId()
        FirestoreUtil.getProfileImageUri(userId,
            onSuccess = { imageUrl ->
                // Load the image using Picasso library
                Picasso.get().load(imageUrl).into(view.civProfile)
            },
            onFailure = { exception ->
                // Handle failure, if needed
            }
        )

        // Launch image selection when civProfile is clicked
        view.civProfile.setOnClickListener {
            selectImage.launch("image/*")
        }
        view.EditButton.setOnClickListener {
            val intent= Intent(context, EditProfileActivity::class.java)
            startActivity(intent)
        }

        return view.root
    }
}
