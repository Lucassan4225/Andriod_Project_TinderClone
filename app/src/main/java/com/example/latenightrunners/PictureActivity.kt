////package com.example.latenightrunners
////
////import android.content.Intent
////import android.net.Uri
////import android.os.Bundle
////import android.widget.Toast
////import androidx.appcompat.app.AppCompatActivity
////import com.example.latenightrunners.databinding.ActivityPictureBinding
////import com.example.latenightrunners.firestore.com.example.latenightrunners.firestore.FirestoreUtil
////
////class PictureActivity : AppCompatActivity() {
////    private lateinit var view: ActivityPictureBinding
////
////    private var gender: String? = null
////    private var interestedGender: String? = null
////    private var name: String? = null
////    private var birthday: String? = null
////    private var age: String? = null
////    private var imageUri: Uri? = null
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        view = ActivityPictureBinding.inflate(layoutInflater)
////        setContentView(view.root)
////
////        gender = intent.getStringExtra("gender")
////        interestedGender = intent.getStringExtra("interestedGender")
////        name = intent.getStringExtra("name")
////        birthday = intent.getStringExtra("birthday")
////        age = intent.getStringExtra("age")
////
////        view.btnNextToMain.setOnClickListener {
////            saveUserDataToFirestore()
////        }
////
////        view.addImage.setOnClickListener {
////            openGallery()
////        }
////    }
////
////    private fun saveUserDataToFirestore() {
////        if (gender != null && interestedGender != null && name != null && birthday != null && age != null && imageUri != null) {
////            val userId = com.example.latenightrunners.firestore.FirestoreUtil.getUserId()
////            val userData = mapOf(
////                "gender" to gender!!,
////                "interestedGender" to interestedGender!!,
////                "name" to name!!,
////                "birthday" to birthday!!,
////                "age" to age!!
////                // Add other attributes as needed
////            )
////
////            com.example.latenightrunners.firestore.FirestoreUtil.saveData("users", userId, userData,
////                onSuccess = {
////                    Toast.makeText(this, "Profile Updated.", Toast.LENGTH_SHORT).show()
////                    val intent = Intent(this, MainNavigationActivity::class.java)
////                    startActivity(intent)
////                    finish()
////                },
////                onFailure = { e ->
////                    Toast.makeText(this, "Error saving data to Firestore: $e", Toast.LENGTH_SHORT).show()
////                }
////            )
////
////            com.example.latenightrunners.firestore.FirestoreUtil.saveImage("images", userId, imageUri!!, "profile_picture",
////                onSuccess = {
////                    // Handle success, if needed
////                },
////                onFailure = { e ->
////                    Toast.makeText(this, "Error saving image to Firestore: $e", Toast.LENGTH_SHORT).show()
////                }
////            )
////        } else {
////            Toast.makeText(this, "Some data is missing. Cannot save to Firestore.", Toast.LENGTH_SHORT).show()
////        }
////    }
////
////    private fun openGallery() {
////        val intent = Intent(Intent.ACTION_GET_CONTENT)
////        intent.type = "image/*"
////        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
////    }
////
////    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        super.onActivityResult(requestCode, resultCode, data)
////        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
////            data?.data?.let { uri ->
////                imageUri = uri
////                view.ivShowAddedProfile.setImageURI(uri)
////            }
////        }
////    }
////
////    companion object {
////        private const val REQUEST_CODE_PICK_IMAGE = 1001
////    }
////}
//
//package com.example.latenightrunners
//
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityPictureBinding
//
//
//class PictureActivity : AppCompatActivity() {
//    private lateinit var view: ActivityPictureBinding
//
//    private var gender: String? = null
//    private var interestedGender: String? = null
//    private var name: String? = null
//    private var birthday: String? = null
//    private var age: String? = null
//    private var imageUri: Uri? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        view = ActivityPictureBinding.inflate(layoutInflater)
//        setContentView(view.root)
//
//        gender = intent.getStringExtra("gender")
//        interestedGender = intent.getStringExtra("interestedGender")
//        name = intent.getStringExtra("name")
//        birthday = intent.getStringExtra("birthday")
//        age = intent.getStringExtra("age")
//
//        view.btnNextToMain.setOnClickListener {
//            saveUserDataToFirestore()
//        }
//
//        view.addImage.setOnClickListener {
//            openGallery()
//        }
//    }
//
//    private fun saveUserDataToFirestore() {
//        if (gender != null && interestedGender != null && name != null && birthday != null && age != null && imageUri != null) {
//            val userId = com.example.latenightrunners.firestore.FirestoreUtil.getUserId()
//            val userData = mapOf(
//                "gender" to gender!!,
//                "interestedGender" to interestedGender!!,
//                "name" to name!!,
//                "birthday" to birthday!!,
//                "age" to age!!
//                // Add other attributes as needed
//            )
//
//            com.example.latenightrunners.firestore.FirestoreUtil.saveUserData(userId, userData,
//                onSuccess = {
//                    Toast.makeText(this, "Profile Updated.", Toast.LENGTH_SHORT).show()
//                    // Now save the image after user data is successfully saved
//                    saveImageToFirestore(userId)
//                },
//                onFailure = { e ->
//                    Toast.makeText(this, "Error saving data to Firestore: $e", Toast.LENGTH_SHORT).show()
//                }
//            )
//        } else {
//            Toast.makeText(this, "Some data is missing. Cannot save to Firestore.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun saveImageToFirestore(userId: String) {
//        com.example.latenightrunners.firestore.FirestoreUtil.saveImage("images", userId, imageUri!!, "profile_picture",
//            onSuccess = {
//                // Handle success, if needed
//                val intent = Intent(this, MainNavigationActivity::class.java)
//                startActivity(intent)
//                finish()
//            },
//            onFailure = { e ->
//                Toast.makeText(this, "Error saving image to Firestore: $e", Toast.LENGTH_SHORT).show()
//            }
//        )
//    }
//
//    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
//            data?.data?.let { uri ->
//                imageUri = uri
//                view.ivShowAddedProfile.setImageURI(uri)
//            }
//        }
//    }
//
//    companion object {
//        private const val REQUEST_CODE_PICK_IMAGE = 1001
//    }
//}

//// ORIGINAL 1 \/\/\/\/\/\/\/
//package com.example.latenightrunners
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityPictureBinding
//
//class PictureActivity : AppCompatActivity() {
//    private lateinit var view: ActivityPictureBinding
//    private var gender: String? = null
//    private var interestedGender: String? = null
//    private var name: String? = null
//    private var birthday: String? = null
//    private var age: String? = null
//    private var imageUri: Uri? = null
//    private var minAgePre: String = "18"
//    private var maxAgePre: String = "100"
//    private var distancePre: String = "100"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        view = ActivityPictureBinding.inflate(layoutInflater)
//        setContentView(view.root)
//        gender = intent.getStringExtra("gender")
//        interestedGender = intent.getStringExtra("interestedGender")
//        name = intent.getStringExtra("name")
//        birthday = intent.getStringExtra("birthday")
//        age = intent.getStringExtra("age")
//        view.btnNextToMain.setOnClickListener {
//            saveUserDataToFirestore()
//        }
//        view.addImage.setOnClickListener {
//            openGallery()
//        }
//    }
//    private fun saveUserDataToFirestore() {
//        if (gender != null && interestedGender != null && name != null && birthday != null && age != null && imageUri != null) {
//            val userId = com.example.latenightrunners.firestore.FirestoreUtil.getUserId()
//            val userData = mapOf(
//                "gender" to gender!!,
//                "interestedGender" to interestedGender!!,
//                "name" to name!!,
//                "birthday" to birthday!!,
//                "age" to age!!,
//                "minAgePre" to minAgePre,
//                "maxAgePre" to maxAgePre,
//                "distancePre" to distancePre
//// Add other attributes as needed
//            )
//            com.example.latenightrunners.firestore.FirestoreUtil.saveUserData(userId, userData,
//                onSuccess = {
//                    Toast.makeText(this, "Profile Updated.", Toast.LENGTH_SHORT).show()
//// Now save the image after user data is successfully saved
//                    saveImageToFirestore(userId)
//                },
//                onFailure = { e ->
//                    Toast.makeText(this, "Error saving data to Firestore: $e", Toast.LENGTH_SHORT).show()
//                }
//            )
//        } else {
//            Toast.makeText(this, "Some data is missing. Cannot save to Firestore.", Toast.LENGTH_SHORT).show()
//        }
//    }
//    private fun saveImageToFirestore(userId: String) {
//        com.example.latenightrunners.firestore.FirestoreUtil.saveImage("images", userId, imageUri!!, "profile_picture",
//            onSuccess = {
//// Handle success, if needed
//                val intent = Intent(this, MainNavigationActivity::class.java)
//                startActivity(intent)
//                finish()
//            },
//            onFailure = { e ->
//                Toast.makeText(this, "Error saving image to Firestore: $e", Toast.LENGTH_SHORT).show()
//            }
//        )
//    }
//    private fun openGallery() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
//            data?.data?.let { uri ->
//                imageUri = uri
//                view.ivShowAddedProfile.setImageURI(uri)
//            }
//        }
//    }
//    companion object {
//        private const val REQUEST_CODE_PICK_IMAGE = 1001
//    }
//}
//
//// ORIGINAL 1 /\ /\ /\ /\ /\ /\ /\ /\ /\ /\

package com.example.latenightrunners
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityPictureBinding
import com.example.latenightrunners.firestore.FirestoreUtil

class PictureActivity : AppCompatActivity() {
    private lateinit var view: ActivityPictureBinding
    private var gender: String? = null
    private var interestedGender: String? = null
    private var name: String? = null
    private var birthday: String? = null
    private var age: String? = null
    private var imageUri: Uri? = null
    private var minAgePre: String = "18"
    private var maxAgePre: String = "100"
    private var distancePre: String = "100"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(view.root)
        gender = intent.getStringExtra("gender")
        interestedGender = intent.getStringExtra("interestedGender")
        name = intent.getStringExtra("name")
        birthday = intent.getStringExtra("birthday")
        age = intent.getStringExtra("age")
        view.btnNextToMain.setOnClickListener {
            saveUserDataToFirestore()
        }
        view.addImage.setOnClickListener {
            openGallery()
        }
    }
    private fun saveUserDataToFirestore() {
        if (gender != null && interestedGender != null && name != null && birthday != null && age != null && imageUri != null) {
            val userId = FirestoreUtil.getUserId()
            val userData = mapOf(
                "gender" to gender!!,
                "interestedGender" to interestedGender!!,
                "name" to name!!,
                "birthday" to birthday!!,
                "age" to age!!,
                "minAgePre" to minAgePre,
                "maxAgePre" to maxAgePre,
                "distancePre" to distancePre
            )

            FirestoreUtil.saveUserData(userId, userData,
                onSuccess = {
                    Log.d("PictureActivity", "User data saved successfully.")
                    Toast.makeText(this, "Profile Updated.", Toast.LENGTH_SHORT).show()
                    saveImageToFirestore(userId)
                },
                onFailure = { e ->
                    Log.e("PictureActivity", "Error saving data to Firestore: $e")
                    Toast.makeText(this, "Error saving data to Firestore: $e", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            Toast.makeText(this, "Some data is missing. Cannot save to Firestore.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveImageToFirestore(userId: String) {
        FirestoreUtil.saveImage("images", userId, imageUri!!, "profile_picture",
            onSuccess = {
                Log.d("PictureActivity", "Image saved successfully.")
                val intent = Intent(this, MainNavigationActivity::class.java)
                startActivity(intent)
                finish()
            },
            onFailure = { e ->
                Log.e("PictureActivity", "Error saving image to Firestore: $e")
                Toast.makeText(this, "Error saving image to Firestore: $e", Toast.LENGTH_SHORT).show()
            }
        )
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK) {
            data?.data?.let { uri ->
                imageUri = uri
                view.ivShowAddedProfile.setImageURI(uri)
            }
        }
    }
    companion object {
        private const val REQUEST_CODE_PICK_IMAGE = 1001
    }
}
