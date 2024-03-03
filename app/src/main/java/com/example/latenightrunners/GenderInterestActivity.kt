//package com.example.latenightrunners
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.renderscript.ScriptGroup.Binding
//import com.example.latenightrunners.databinding.ActivityGenderInterestBinding
//import com.example.latenightrunners.databinding.ActivityNameSetUpBinding
//
//class GenderInterestActivity : AppCompatActivity() {
//    private val view: ActivityGenderInterestBinding by lazy{ ActivityGenderInterestBinding.inflate(layoutInflater)}
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(view.root)
//
//
//        view.btnIMen.setOnClickListener {
//            val intent = Intent(this,NameSetUpActivity::class.java)
//            startActivity(intent)
//        }
//        view.btnIWomen.setOnClickListener {
//            val intent = Intent(this,NameSetUpActivity::class.java)
//            startActivity(intent)
//        }
//        view.btnIEveryone.setOnClickListener {
//            val intent = Intent(this,NameSetUpActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//}
package com.example.latenightrunners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityGenderInterestBinding

class GenderInterestActivity : AppCompatActivity() {
    private val view: ActivityGenderInterestBinding by lazy { ActivityGenderInterestBinding.inflate(layoutInflater) }
    private var gender: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        gender = intent.getStringExtra("gender")

        view.btnIMen.setOnClickListener {
            startPictureActivity("Men")
        }

        view.btnIWomen.setOnClickListener {
            startPictureActivity("Women")
        }

        view.btnIEveryone.setOnClickListener {
            startPictureActivity("Everyone")
        }
    }

    private fun startPictureActivity(interestedGender: String) {
        val intent = Intent(this, NameSetUpActivity::class.java).apply {
            putExtra("interestedGender", interestedGender)
            putExtra("gender", gender)
        }
        startActivity(intent)
    }
}
