package com.example.latenightrunners

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.firestore.FirestoreUtil
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class DiscoverSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover_settings)

        val arrowIcon = findViewById<ImageView>(R.id.arrow_icon)
        val seekBarAgeRange = findViewById<RangeSlider>(R.id.seekBarAgeRange)
        val sliderDistanceRange = findViewById<Slider>(R.id.sliderDistanceRange)

        arrowIcon.setOnClickListener {

            val minAgePre = seekBarAgeRange.values[0].toInt().toString()
            val maxAgePre = seekBarAgeRange.values[1].toInt().toString()
            val distance = sliderDistanceRange.value.toInt().toString()


            savePreferencesToFirestore(minAgePre, maxAgePre, distance)
        }
    }

    private fun savePreferencesToFirestore(minAgePre: String, maxAgePre: String, distance: String) {
        val userId = FirestoreUtil.getUserId()
        val userData = mapOf(
            "minAgePre" to minAgePre,
            "maxAgePre" to maxAgePre,
            "distance" to distance
        )

        FirestoreUtil.db.collection("users")
            .document(userId)
            .update(userData)
            .addOnSuccessListener {
                runOnUiThread {
                    Toast.makeText(this, "Preferences updated", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainNavigationActivity::class.java))
                }
            }
            .addOnFailureListener {
                runOnUiThread {
                    Toast.makeText(this, "Failed to update preferences", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
