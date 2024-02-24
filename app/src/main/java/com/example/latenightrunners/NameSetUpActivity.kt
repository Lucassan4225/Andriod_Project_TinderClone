//package com.example.latenightrunners
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.latenightrunners.databinding.ActivityNameSetUpBinding
//
//class NameSetUpActivity : AppCompatActivity() {
//    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//
//        view.btnNext2.setOnClickListener {
//            val intent = Intent(this,BDayActivity::class.java)
//            startActivity(intent)
//        }
//
//
//    }
//}
package com.example.latenightrunners

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityNameSetUpBinding
import com.example.latenightrunners.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class NameSetUpActivity : AppCompatActivity() {
    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnNext2.setOnClickListener {
            // Retrieve the name from the EditText
            val name = view.editTextText.text.toString().trim()

            // Create an instance of UserModel with the name
            val userModel = UserModel(name = name)

            // Save the UserModel to Firebase
            saveUserToFirebase(userModel)

            // Proceed to the next activity
            val intent = Intent(this, BDayActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveUserToFirebase(userModel: UserModel) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId)
            databaseReference.setValue(userModel)
                .addOnSuccessListener {
                    // Data saved successfully
                }
                .addOnFailureListener {
                    // Failed to save data
                }
        }
    }
}
