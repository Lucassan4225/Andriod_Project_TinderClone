package com.example.latenightrunners
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityPhoneInputBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class PhoneInputActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityPhoneInputBinding
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityPhoneInputBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//
//        if (auth.currentUser != null) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//
//        binding.btnPhoneInputContinue.setOnClickListener {
//            val phoneNumber = binding.etPhoneInput.text.toString().trim()
//            if (phoneNumber.isEmpty()) {
//                Toast.makeText(this, "Please enter your Number!!", Toast.LENGTH_SHORT).show()
//            } else {
//                val intent = Intent(this, OTPActivity::class.java)
//                intent.putExtra("number", phoneNumber)
//                startActivity(intent)
//            }
//        }
//    }
//}
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityPhoneInputBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class PhoneInputActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityPhoneInputBinding
//    private lateinit var auth: FirebaseAuth
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityPhoneInputBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//
//        if (auth.currentUser != null) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//
//        binding.btnPhoneInputContinue.setOnClickListener {
//            val phoneNumber = binding.etPhoneInput.text.toString().trim()
//
//            if (phoneNumber.isEmpty()) {
//                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
//            } else {
//                val intent = Intent(this, OTPActivity::class.java)
//                intent.putExtra("phoneNumber", phoneNumber)
//                startActivity(intent)
//            }
//        }
//    }
//}
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityPhoneInputBinding
import com.google.firebase.auth.FirebaseAuth

class PhoneInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnPhoneInputContinue.setOnClickListener {
            val inputPhoneNumber = binding.etPhoneInput.text.toString().trim()
            if (inputPhoneNumber.isEmpty()) {
                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
            } else {
                val phoneNumber = "+66$inputPhoneNumber"
                val intent = Intent(this, OTPActivity::class.java)
                intent.putExtra("phoneNumber", phoneNumber)
                startActivity(intent)
            }
        }
    }
}

