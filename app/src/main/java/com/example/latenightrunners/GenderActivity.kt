//package com.example.latenightrunners
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.latenightrunners.databinding.ActivityGenderBinding
//
//class GenderActivity : AppCompatActivity() {
//    private val view: ActivityGenderBinding by lazy{ActivityGenderBinding.inflate(layoutInflater)}
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//        view.btnGMen.setOnClickListener {
//            val intent = Intent(this,GenderInterestActivity::class.java)
//            startActivity(intent)
//        }
//        view.btnGWomen.setOnClickListener {
//            val intent = Intent(this,GenderInterestActivity::class.java)
//            startActivity(intent)
//        }
//    }
//    //Choice: Gender(Men,Women)
//
//}
package com.example.latenightrunners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityGenderBinding

class GenderActivity : AppCompatActivity() {
    private val view: ActivityGenderBinding by lazy { ActivityGenderBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnGMen.setOnClickListener {
            startPictureActivity("Men")
        }

        view.btnGWomen.setOnClickListener {
            startPictureActivity("Women")
        }
    }

    private fun startPictureActivity(gender: String) {
        val intent = Intent(this, GenderInterestActivity::class.java).apply {
            putExtra("gender", gender)
        }
        startActivity(intent)
    }
}
