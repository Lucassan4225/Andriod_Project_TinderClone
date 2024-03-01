//////package com.example.latenightrunners.fragments
//////
//////import android.net.Uri
//////import android.os.Bundle
//////import android.view.LayoutInflater
//////import android.view.View
//////import android.view.ViewGroup
//////import androidx.activity.result.contract.ActivityResultContracts
//////import androidx.fragment.app.Fragment
//////import com.example.latenightrunners.databinding.ProfileFragmentBinding
//////
//////class ProfileFragment: Fragment() {
//////    private lateinit var view: ProfileFragmentBinding
//////    private var imageUri: Uri? = null
//////    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()){
//////        imageUri = it
//////
//////        view.civProfile.setImageURI(imageUri)
//////    }
//////
//////    override fun onCreateView(
//////        inflater: LayoutInflater,
//////        container: ViewGroup?,
//////        savedInstanceState: Bundle?
//////    ): View {
//////        view = ProfileFragmentBinding.inflate(inflater,container,false)
//////        return view.root
////////
////////        view.civProfile.setOnClickListener {
////////            selectImage.launch("image/*")
////////        }
////////
////////        view.   .setOnClickListener{
////////
////////        }
//////    }
//////}
////package com.example.latenightrunners.fragments
////
////import android.content.Intent
////import android.net.Uri
////import android.os.Bundle
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.fragment.app.Fragment
////import com.example.latenightrunners.EditProfileActivity
////import com.example.latenightrunners.databinding.ProfileFragmentBinding
////import com.example.latenightrunners.firestore.FirestoreUtil
////import com.squareup.picasso.Picasso
////
////class ProfileFragment : Fragment() {
////    private lateinit var view: ProfileFragmentBinding
////    private var imageUri: Uri? = null
////    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
////        imageUri = uri
////        view.civProfile.setImageURI(imageUri)
////    }
////
////    override fun onCreateView(
////        inflater: LayoutInflater,
////        container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View {
////        view = ProfileFragmentBinding.inflate(inflater, container, false)
////
////        // Retrieve user's image from Firestore and load it into civProfile
////        val userId = FirestoreUtil.getUserId()
////        FirestoreUtil.getProfileImageUri(userId,
////            onSuccess = { imageUrl ->
////                // Load the image using Picasso library
////                Picasso.get().load(imageUrl).into(view.civProfile)
////            },
////            onFailure = { exception ->
////                // Handle failure, if needed
////            }
////        )
////
////        // Launch image selection when civProfile is clicked
////        view.civProfile.setOnClickListener {
////            selectImage.launch("image/*")
////        }
////        view.EditButton.setOnClickListener {
////            val intent= Intent(context, EditProfileActivity::class.java)
////            startActivity(intent)
////        }
////
////        return view.root
////    }
////}
////package com.example.latenightrunners.fragments
////
////import android.content.Intent
////import android.net.Uri
////import android.os.Bundle
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.fragment.app.Fragment
////import com.example.latenightrunners.EditProfileActivity
////import com.example.latenightrunners.databinding.ProfileFragmentBinding
////import com.example.latenightrunners.firestore.FirestoreUtil
////import com.squareup.picasso.Picasso
////
////class ProfileFragment : Fragment() {
////    private lateinit var fragmentBinding: ProfileFragmentBinding
////    private var imageUri: Uri? = null
////    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
////        imageUri = uri
////        fragmentBinding.civProfile.setImageURI(imageUri)
////    }
////
////    override fun onCreateView(
////        inflater: LayoutInflater,
////        container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        fragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)
////        return fragmentBinding.root
////    }
////
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////
////        // Retrieve user's image from Firestore and load it into civProfile
////        val userId = FirestoreUtil.getUserId()
////        FirestoreUtil.getProfileImageUri(userId,
////            onSuccess = { imageUrl ->
////                // Load the image using Picasso library
////                Picasso.get().load(imageUrl).into(fragmentBinding.civProfile)
////            },
////            onFailure = { exception ->
////                // Handle failure, if needed
////            }
////        )
////
////        // Launch image selection when civProfile is clicked
////        fragmentBinding.civProfile.setOnClickListener {
////            selectImage.launch("image/*")
////        }
////        fragmentBinding.EditButton.setOnClickListener {
////            val intent= Intent(context, EditProfileActivity::class.java)
////            startActivity(intent)
////        }
////    }
////}
//
////package com.example.latenightrunners.fragments
////
////import android.content.Intent
////import android.net.Uri
////import android.os.Bundle
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.Toast
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.fragment.app.Fragment
////import com.example.latenightrunners.EditProfileActivity
////import com.example.latenightrunners.databinding.ProfileFragmentBinding
////import com.example.latenightrunners.firestore.FirestoreUtil
////import com.squareup.picasso.Picasso
////
////class ProfileFragment : Fragment() {
////    private lateinit var fragmentBinding: ProfileFragmentBinding
////    private val selectImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
////        // Save the edited image URI to Firestore
////        val userId = FirestoreUtil.getUserId()
////        if (uri != null) {
////            FirestoreUtil.saveImage("users", userId, uri, "profile_image",
////                onSuccess = {
////                    // Load the edited image into civProfile
////                    loadImage(userId)
////                },
////                onFailure = { exception ->
////                    // Handle failure, if needed
////                }
////            )
////        }
////    }
////
////    override fun onCreateView(
////        inflater: LayoutInflater,
////        container: ViewGroup?,
////        savedInstanceState: Bundle?
////    ): View? {
////        fragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)
////        return fragmentBinding.root
////    }
////
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////
////        // Load the profile image into civProfile
////        val userId = FirestoreUtil.getUserId()
////        loadImage(userId)
////
////        // Launch image selection when civProfile is clicked
////        fragmentBinding.civProfile.setOnClickListener {
////            selectImage.launch("image/*")
////        }
////
////        fragmentBinding.EditButton.setOnClickListener {
////            val intent= Intent(context, EditProfileActivity::class.java)
////            startActivity(intent)
////        }
////    }
////
////    private fun loadImage(userId: String) {
////        FirestoreUtil.getProfileImageUri(userId,
////            onSuccess = { imageUrl ->
////                // Load the image using Picasso library
////                Picasso.get().load(imageUrl).into(fragmentBinding.civProfile)
////
////            },
////            onFailure = { exception ->
////                // Handle failure, if needed
////
////            }
////        )
////    }
////}
//package com.example.latenightrunners.fragments
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.fragment.app.Fragment
//import com.example.latenightrunners.EditProfileActivity
//import com.example.latenightrunners.R
//import com.example.latenightrunners.databinding.ProfileFragmentBinding
//
//import com.squareup.picasso.Picasso
//
//class ProfileFragment : Fragment() {
//
//    private lateinit var fragmentBinding: ProfileFragmentBinding
//
//    private val selectImage =
//        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            uri?.let { imageUri ->
//                val userId = FirestoreUtil.getUserId()
//                FirestoreUtil.saveImage(
//                    "users", userId, imageUri, "profile_image",
//                    onSuccess = {
//                        // After saving the image, load it immediately
//                        loadImage(userId)
//                    },
//                    onFailure = { exception ->
//                        // Handle failure, if needed
//                    }
//                )
//            }
//        }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        fragmentBinding = ProfileFragmentBinding.inflate(inflater, container, false)
//        return fragmentBinding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val userId = FirestoreUtil.getUserId()
//        loadImage(userId)
//
//        fragmentBinding.civProfile.setOnClickListener {
//            selectImage.launch("users/*")
//        }
//
//        fragmentBinding.EditButton.setOnClickListener {
//            val intent = Intent(context, EditProfileActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun loadImage(userId: String) {
//        FirestoreUtil.getProfileImageUri(
//            userId,
//            onSuccess = { imageUrl ->
//                // Load the image using Picasso library
//                if (!imageUrl.isNullOrEmpty()) {
//                    Picasso.get().load(imageUrl).into(fragmentBinding.civProfile)
//                } else {
//                    Picasso.get().load(R.drawable.test3).into(fragmentBinding.civProfile)
//                    // If there is no image URL available, you can load a default image here
//                    // Picasso.get().load(R.drawable.default_profile_image).into(fragmentBinding.civProfile)
//                }
//            },
//            onFailure = { exception ->
//                // Handle failure, if needed
//            }
//        )
//    }
//}
//
//
//
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
        FirestoreUtil.getUserData(userId,
            onSuccess = { userData ->
                // Display user's name and age
                val name = userData["name"] as String?
                val age = userData["age"] as String?
                view.tvProfileName.text = name
                view.tvProfileAge.text = age
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