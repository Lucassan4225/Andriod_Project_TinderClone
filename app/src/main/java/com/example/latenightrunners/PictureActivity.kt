//package com.example.latenightrunners
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityPictureBinding
//import com.example.latenightrunners.firestore.FirestoreUtil
//
//class PictureActivity : AppCompatActivity() {
//    private val view: ActivityPictureBinding by lazy { ActivityPictureBinding.inflate(layoutInflater) }
//
//    // Variables to store user choices
//    private var gender: String? = null
//    private var interestedGender: String? = null
//    private var name: String? = null
//    private var birthday: String? = null
//    private var age: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//        // Retrieve data from intent extras
//        gender = intent.getStringExtra("gender")
//        interestedGender = intent.getStringExtra("interestedGender")
//        name = intent.getStringExtra("name")
//        birthday = intent.getStringExtra("birthday")
//        age = intent.getStringExtra("age")
//
//        // Example: Saving data to Firestore when a button is clicked
//        view.btnNextToMain.setOnClickListener {
//            saveUserDataToFirestore()
//        }
//    }
//
//    private fun saveUserDataToFirestore() {
//        // Check if all necessary data is available
//
//        if (gender != null && interestedGender != null && name != null && birthday != null && age != null) {
//            val userId = FirestoreUtil.getUserId()
//            val userData = mapOf(
//                "gender" to gender!!,
//                "interestedGender" to interestedGender!!,
//                "name" to name!!,
//                "birthday" to birthday!!,
//                "age" to age!!
//                // Add other attributes as needed
//            )
//
//
//            // Save data to Firestore using FirestoreUtil
//            FirestoreUtil.saveData("users", userId, userData,
//                onSuccess = {
//                    // Handle success, for example, show a success message
//                    println("Data saved successfully to Firestore")
//                    // Navigate to the next activity after data is saved
//                    val intent = Intent(this, MainNavigationActivity::class.java)
//                    startActivity(intent)
//                },
//                onFailure = { e ->
//                    // Handle failure, for example, show an error message
//                    Toast.makeText(this, "Error saving data to Firestore: $e", Toast.LENGTH_SHORT).show()
//                }
//            )
//        } else {
//            // Handle case where data is missing
//            Toast.makeText(this, "Some data is missing. Cannot save to Firestore.", Toast.LENGTH_SHORT).show()
//            val nullVariable = "unkown"
//            if (interestedGender == null ) {
//                val nullVariable = "interestedGender"
//                Toast.makeText(applicationContext, "$nullVariable is null", Toast.LENGTH_SHORT)
//                    .show()
//            }
//            if (gender == null ){
//                val nullVariable = "gender"
//                Toast.makeText(applicationContext, "$nullVariable is null", Toast.LENGTH_SHORT).show()
//            }
//
//
//
//
//            if (name == null){
//                val nullVariable ="name"
//                Toast.makeText(applicationContext, "$nullVariable is null", Toast.LENGTH_SHORT).show()
//
//
//                // Display toast
//
//            }
//            if (birthday == null){
//                val nullVariable = "birthday"
//                Toast.makeText(applicationContext, "$nullVariable is null", Toast.LENGTH_SHORT).show()
//
//
//                // Display toast
//
//            }
//            if (age == null){
//                val nullVariable ="age"
//                Toast.makeText(applicationContext, "$nullVariable is null", Toast.LENGTH_SHORT).show()
//
//
//                // Display toast
//
//            }
//
//        }
//    }
//
//}
//
package com.example.latenightrunners

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityPictureBinding
import com.example.latenightrunners.firestore.FirestoreUtil

class PictureActivity : AppCompatActivity() {
    private lateinit var view: ActivityPictureBinding

    // Variables to store user choices
    private var gender: String? = null
    private var interestedGender: String? = null
    private var name: String? = null
    private var birthday: String? = null
    private var age: String? = null
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityPictureBinding.inflate(layoutInflater)
        setContentView(view.root)

        // Retrieve data from intent extras
        gender = intent.getStringExtra("gender")
        interestedGender = intent.getStringExtra("interestedGender")
        name = intent.getStringExtra("name")
        birthday = intent.getStringExtra("birthday")
        age = intent.getStringExtra("age")

        // Example: Saving data to Firestore when a button is clicked
        view.btnNextToMain.setOnClickListener {
            saveUserDataToFirestore()
        }

        // Handle image click to open gallery
        view.addImage.setOnClickListener {
            openGallery()
        }
    }

    private fun saveUserDataToFirestore() {
        // Check if all necessary data is available
        if (gender != null && interestedGender != null && name != null && birthday != null && age != null && imageUri != null) {
            val userId = FirestoreUtil.getUserId()
            val userData = mapOf(
                "gender" to gender!!,
                "interestedGender" to interestedGender!!,
                "name" to name!!,
                "birthday" to birthday!!,
                "age" to age!!
                // Add other attributes as needed
            )

            // Save data to Firestore using FirestoreUtil
            FirestoreUtil.saveData("users", userId, userData,
                onSuccess = {
                    // Handle success, for example, show a success message
                    println("Data saved successfully to Firestore")
                    // Navigate to the next activity after data is saved
                    val intent = Intent(this, MainNavigationActivity::class.java)
                    startActivity(intent)
                },
                onFailure = { e ->
                    // Handle failure, for example, show an error message
                    Toast.makeText(this, "Error saving data to Firestore: $e", Toast.LENGTH_SHORT).show()
                }
            )

            // Save image to Firestore using FirestoreUtil
            FirestoreUtil.saveImage("images", userId, imageUri!!, "profile_picture",
                onSuccess = {
                    // Handle success, if needed
                },
                onFailure = { e ->
                    // Handle failure, if needed
                    Toast.makeText(this, "Error saving image to Firestore: $e", Toast.LENGTH_SHORT).show()
                }
            )
        } else {
            // Handle case where data is missing
            Toast.makeText(this, "Some data is missing. Cannot save to Firestore.", Toast.LENGTH_SHORT).show()
        }
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
