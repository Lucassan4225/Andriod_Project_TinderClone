package com.example.latenightrunners

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityEditProfileBinding
import com.example.latenightrunners.firestore.FirestoreUtil
import com.squareup.picasso.Picasso

class EditProfileActivity : AppCompatActivity() {
    private lateinit var view: ActivityEditProfileBinding
    private var bio: String? = null
    private var interest: String? = null
    private var language: String? = null
    private var height: String? = null
    private var blood: String? = null
    private var gym: String? = null
    private var sleep: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(view.root)
        bio = intent.getStringExtra("bio")
        interest = intent.getStringExtra("interest")
        height = intent.getStringExtra("height")
        language = intent.getStringExtra("language")
        blood = intent.getStringExtra("blood")
        gym = intent.getStringExtra("gym")
        sleep = intent.getStringExtra("sleep")

        view.buttonDone.setOnClickListener {
            updateUserDataAndNavigateBack()
        }

        // Load user's image
        loadUserProfileImage()
    }

    private fun loadUserProfileImage() {
        // Get the current user ID
        val userId = FirestoreUtil.getUserId()

        // Retrieve user's image URL from Firestore
        FirestoreUtil.getProfileImageUri(userId,
            onSuccess = { imageUrl ->
                // Load the image using Picasso library
                Picasso.get().load(imageUrl).into(view.editProfileImage)
            },
            onFailure = { exception ->
                // Handle failure to retrieve image URL, if needed
                Log.e("EditProfileActivity", "Error loading user image", exception)
            }
        )
    }

    private fun updateUserDataAndNavigateBack() {
        // Get the current user ID
        val userId = FirestoreUtil.getUserId()

        // Retrieve existing user data from Firestore
        FirestoreUtil.getUserData(userId,
            onSuccess = { userData ->
                // Create a mutable map to hold the updated user data
                val updatedUserData = userData.toMutableMap()

                // Retrieve data from EditText fields and update the map
                updateField(updatedUserData, R.id.EditProfileBio, "EditProfileBio")
                updateField(updatedUserData, R.id.EditProfileInterest, "EditProfileInterest")
                updateField(updatedUserData, R.id.EditProfileHeight, "EditProfileHeight")
                updateField(updatedUserData, R.id.EditProfileLanguage, "EditProfileLanguage")
                updateField(updatedUserData, R.id.EditProfileBlood, "EditProfileBlood")
                updateField(updatedUserData, R.id.EditProfileGym, "EditProfileGym")
                updateField(updatedUserData, R.id.EditProfileSleep, "EditProfileSleep")

                // Update user data in Firestore only if there are changes
                if (updatedUserData != userData) {
                    FirestoreUtil.saveUserData(userId, updatedUserData,
                        onSuccess = {
                            // Data updated successfully, navigate back to ProfileFragment
                            finish()
                        },
                        onFailure = { exception ->
                            // Handle failure
                            Log.e("EditProfileActivity", "Error updating user data", exception)
                            // You can show an error message or handle the failure as needed
                        }
                    )
                } else {
                    // No changes to update, simply navigate back to ProfileFragment
                    finish()
                }
            },
            onFailure = { exception ->
                // Handle failure to retrieve existing user data
                Log.e("EditProfileActivity", "Error fetching user data", exception)
                // You can show an error message or handle the failure as needed
            }
        )
    }

    private fun updateField(updatedUserData: MutableMap<String, Any>, editTextId: Int, field: String) {
        val editText = findViewById<EditText>(editTextId)
        val fieldValue = editText.text.toString().trim()
        if (fieldValue.isNotEmpty()) {
            updatedUserData[field] = fieldValue
        }
    }

}
