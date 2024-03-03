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


        loadUserProfileImage()
    }

    private fun loadUserProfileImage() {

        val userId = FirestoreUtil.getUserId()


        FirestoreUtil.getProfileImageUri(userId,
            onSuccess = { imageUrl ->

                Picasso.get().load(imageUrl).into(view.editProfileImage)
            },
            onFailure = { exception ->

                Log.e("EditProfileActivity", "Error loading user image", exception)
            }
        )
    }

    private fun updateUserDataAndNavigateBack() {

        val userId = FirestoreUtil.getUserId()


        FirestoreUtil.getUserData(userId,
            onSuccess = { userData ->

                val updatedUserData = userData.toMutableMap()


                updateField(updatedUserData, R.id.EditProfileBio, "EditProfileBio")
                updateField(updatedUserData, R.id.EditProfileInterest, "EditProfileInterest")
                updateField(updatedUserData, R.id.EditProfileHeight, "EditProfileHeight")
                updateField(updatedUserData, R.id.EditProfileLanguage, "EditProfileLanguage")
                updateField(updatedUserData, R.id.EditProfileBlood, "EditProfileBlood")
                updateField(updatedUserData, R.id.EditProfileGym, "EditProfileGym")
                updateField(updatedUserData, R.id.EditProfileSleep, "EditProfileSleep")


                if (updatedUserData != userData) {
                    FirestoreUtil.saveUserData(userId, updatedUserData,
                        onSuccess = {

                            finish()
                        },
                        onFailure = { exception ->

                            Log.e("EditProfileActivity", "Error updating user data", exception)

                        }
                    )
                } else {
                    finish()
                }
            },
            onFailure = { exception ->

                Log.e("EditProfileActivity", "Error fetching user data", exception)

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
