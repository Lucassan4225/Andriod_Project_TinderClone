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


        db = Firebase.firestore


        fetchUserData()


        val downButton = findViewById<ImageView>(R.id.DownButton)
        downButton.setOnClickListener {

            finish()
        }
    }

    private fun fetchUserData() {
        val userId = FirestoreUtil.getUserId()
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { documentSnapshot ->

                val userData = documentSnapshot.data


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

            }
    }
}
