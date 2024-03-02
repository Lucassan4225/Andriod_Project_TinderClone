////package com.example.latenightrunners
////
////import android.content.Intent
////import android.os.Bundle
////import androidx.appcompat.app.AppCompatActivity
////import com.example.latenightrunners.databinding.ActivityBdayBinding
////import com.example.latenightrunners.firestore.com.example.latenightrunners.firestore.FirestoreUtil
////import java.text.SimpleDateFormat
////import java.util.*
////
////class BDayActivity : AppCompatActivity() {
////    private val view: ActivityBdayBinding by lazy { ActivityBdayBinding.inflate(layoutInflater) }
////
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(view.root)
////
////        view.btnLoginPh.setOnClickListener {
////            // Get the birthday date from the EditText
////            val birthdayString = view.editTextDate.text.toString()
////
////            // Convert the birthday string to a Date object
////            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
////            val birthdayDate = dateFormat.parse(birthdayString)
////
////            // Calculate the age
////            val age = calculateAge(birthdayDate)
////
////            // Save the birthday and age to Firestore
////            val userData = mapOf(
////                "birthday" to birthdayString,
////                "age" to age.toString()
////                // Add other attributes as needed
////            )
////            com.example.latenightrunners.firestore.FirestoreUtil.saveData("users", userData,
////                onSuccess = {
////                    // On success, navigate to the next activity
////                    val intent = Intent(this, MainNavigationActivity::class.java)
////                    startActivity(intent)
////                },
////                onFailure = { e ->
////                    // Handle failure, for example, show an error message
////                    println("Error saving data to Firestore: $e")
////                }
////            )
////        }
////    }
////
////    private fun calculateAge(birthday: Date): Int {
////        val today = Calendar.getInstance()
////        val birthDate = Calendar.getInstance().apply {
////            time = birthday
////        }
////        var age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
////        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
////            age--
////        }
////        return age
////    }
////}
//// BDayActivity.kt
//
//package com.example.latenightrunners
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityBdayBinding
//import com.example.latenightrunners.firestore.com.example.latenightrunners.firestore.FirestoreUtil
//import java.text.SimpleDateFormat
//import java.util.*
//
//class BDayActivity : AppCompatActivity() {
//    private val view: ActivityBdayBinding by lazy { ActivityBdayBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//        view.btnLoginPh.setOnClickListener {
//            // Get the birthday date from the EditText
//            val birthdayString = view.editTextDate.text.toString()
//
//            // Convert the birthday string to a Date object
//            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
//            val birthdayDate = dateFormat.parse(birthdayString)
//
//            // Calculate the age
//            val age = calculateAge(birthdayDate)
//
//            // Get the user ID from your authentication system
//            val userId = getUserId()
//
//            val userData = mapOf(
//                "birthday" to birthdayString,
//                "age" to age.toString()
//                // Add other attributes as needed
//            )
//
//            // Save data with user ID as document ID
//            com.example.latenightrunners.firestore.FirestoreUtil.saveData("users", userId, userData,
//                onSuccess = {
//                    // On success, navigate to the next activity
//                    val intent = Intent(this, MainNavigationActivity::class.java)
//                    startActivity(intent)
//                },
//                onFailure = { e ->
//                    // Handle failure, for example, show an error message
//                    println("Error saving data to Firestore: $e")
//                }
//            )
//        }
//    }
//
//    private fun calculateAge(birthday: Date): Int {
//        val today = Calendar.getInstance()
//        val birthDate = Calendar.getInstance().apply {
//            time = birthday
//        }
//        var age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
//        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
//            age--
//        }
//        return age
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
import com.example.latenightrunners.databinding.ActivityBdayBinding
import java.text.SimpleDateFormat
import java.util.*

class BDayActivity : AppCompatActivity() {
    private val view: ActivityBdayBinding by lazy { ActivityBdayBinding.inflate(layoutInflater) }
    private var gender: String? = null
    private var interestedGender: String? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        gender = intent.getStringExtra("gender")
        interestedGender = intent.getStringExtra("interestedGender")
        name = intent.getStringExtra("name")


        view.btnLoginPh.setOnClickListener {
            val birthdayString = view.editTextDate.text.toString()
            val age = calculateAge(birthdayString)

            val intent = Intent(this, PictureActivity::class.java).apply {
                putExtra("birthday", birthdayString)
                putExtra("age", age.toString())
                putExtra("name", name)
                putExtra("interestedGender", interestedGender)
                putExtra("gender", gender)
            }
            startActivity(intent)
        }
    }

    private fun calculateAge(birthdayString: String): Int {
        val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        val birthdayDate = dateFormat.parse(birthdayString)
        val today = Calendar.getInstance()
        val birthDate = Calendar.getInstance().apply {
            time = birthdayDate
        }
        var age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
            age--
        }
        return age
    }
}
