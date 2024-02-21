package com.example.latenightrunners.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.latenightrunners.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment() {
    private lateinit var view: ProfileFragmentBinding
    private var imageUri: Uri? = null
    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
        imageUri = it

        view.civProfile.setImageURI(imageUri)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = ProfileFragmentBinding.inflate(inflater,container,false)
        return view.root
//
//        view.civProfile.setOnClickListener {
//            selectImage.launch("image/*")
//        }
//
//        view.   .setOnClickListener{
//
//        }
    }
}