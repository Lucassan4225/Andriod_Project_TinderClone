package com.example.latenightrunners

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import android.widget.TextView
import com.example.latenightrunners.firestore.FirestoreUtil
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddInfoActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_info)

        // Initialize Firestore
        db = Firebase.firestore

        // Retrieve user information and populate TextViews
        fetchUserData()

        // Handle click on DownButton to move back to swipeFragment
        val downButton = findViewById<ImageView>(R.id.DownButton)
        downButton.setOnClickListener {
            // Handle navigation back to swipeFragment
            finish()
        }
    }

    private fun fetchUserData() {
        val userId = FirestoreUtil.getUserId()
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                // Retrieve user data
                val userData = documentSnapshot.data

                // Update TextViews with user data
                userData?.let {
                    findViewById<TextView>(R.id.InfoBio).text = it["EditProfileBio"].toString()
                    findViewById<TextView>(R.id.InfoAge).text = it["EditProfileAge"].toString()
                    findViewById<TextView>(R.id.InfoName).text = it["EditProfileName"].toString()
                    findViewById<TextView>(R.id.InfoInterest).text = it["EditProfileInterest"].toString()
                    findViewById<TextView>(R.id.InfoHeight).text = it["EditProfileHeight"].toString()
                    findViewById<TextView>(R.id.InfoLanguage).text = it["EditProfileLanguage"].toString()
                    findViewById<TextView>(R.id.InfoZodiac).text = it["EditProfileZodiac"].toString()
                    findViewById<TextView>(R.id.InfoBlood).text = it["EditProfileBlood"].toString()
                    findViewById<TextView>(R.id.InfoGym).text = it["EditProfileGym"].toString()
                    findViewById<TextView>(R.id.InfoSleep).text = it["EditProfileSleep"].toString()
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure
                // For simplicity, you can log the error or display a toast to the user
            }
    }
}
