////package com.example.latenightrunners
////
////import android.content.Intent
////import androidx.appcompat.app.AppCompatActivity
////import android.os.Bundle
////import com.example.latenightrunners.databinding.ActivityNameSetUpBinding
////import com.example.latenightrunners.firestore.com.example.latenightrunners.firestore.FirestoreUtil
////
////
////class NameSetUpActivity : AppCompatActivity() {
////    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(view.root)
////
////        view.btnNext2.setOnClickListener {
////            val name = view.editTextText.text.toString()
////            val userData = mapOf(
////                "name" to name
////                // Add other attributes as needed
////            )
////            com.example.latenightrunners.firestore.FirestoreUtil.saveData("users", userData,
////                onSuccess = {
////                    // Handle success, for example, navigate to the next activity
////                    val intent = Intent(this, BDayActivity::class.java)
////                    startActivity(intent)
////                },
////                onFailure = { e ->
////                    // Handle failure, for example, show an error message
////                    println("Error adding document: $e")
////                }
////            )
////        }
////    }
////}
//// NameSetUpActivity.kt
//
//package com.example.latenightrunners
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.latenightrunners.databinding.ActivityNameSetUpBinding
//import com.example.latenightrunners.firestore.com.example.latenightrunners.firestore.FirestoreUtil
//
//class NameSetUpActivity : AppCompatActivity() {
//    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//        view.btnNext2.setOnClickListener {
//            val name = view.editTextText.text.toString()
//
//            // Get the user ID from your authentication system
//            val userId = getUserId()
//
//            val userData = mapOf(
//                "name" to name
//                // Add other attributes as needed
//            )
//
//            // Save data with user ID as document ID
//            com.example.latenightrunners.firestore.FirestoreUtil.saveData("users", userId, userData,
//                onSuccess = {
//                    // Handle success, for example, navigate to the next activity
//                    val intent = Intent(this, BDayActivity::class.java)
//                    startActivity(intent)
//                },
//                onFailure = { e ->
//                    // Handle failure, for example, show an error message
//                    println("Error adding document: $e")
//                }
//            )
//        }
//    }
//
//    private fun getUserId(): String {
//        // Implement a method to retrieve the user ID from your authentication system
//        // For example, if you're using Firebase Authentication, you can use FirebaseAuth.getInstance().currentUser?.uid
//        return "exampleUserId" // Replace with actual user ID retrieval logic
//    }
//}
package com.example.latenightrunners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityNameSetUpBinding

class NameSetUpActivity : AppCompatActivity() {
    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }
    private var gender: String? = null
    private var interestedGender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        gender = intent.getStringExtra("gender")
        interestedGender = intent.getStringExtra("interestedGender")

        view.btnNext2.setOnClickListener {
            val name = view.editTextText.text.toString()
            val intent = Intent(this, BDayActivity::class.java).apply {
                putExtra("name", name)
                putExtra("interestedGender", interestedGender)
                putExtra("gender", gender)
            }
            startActivity(intent)
        }
    }
}

