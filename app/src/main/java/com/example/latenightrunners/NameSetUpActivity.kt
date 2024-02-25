package com.example.latenightrunners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityNameSetUpBinding
import com.example.latenightrunners.firestore.FirestoreUtil


class NameSetUpActivity : AppCompatActivity() {
    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnNext2.setOnClickListener {
            val name = view.editTextText.text.toString()
            val userData = mapOf(
                "name" to name
                // Add other attributes as needed
            )
            FirestoreUtil.saveData("users", userData,
                onSuccess = {
                    // Handle success, for example, navigate to the next activity
                    val intent = Intent(this, BDayActivity::class.java)
                    startActivity(intent)
                },
                onFailure = { e ->
                    // Handle failure, for example, show an error message
                    println("Error adding document: $e")
                }
            )
        }
    }
}
