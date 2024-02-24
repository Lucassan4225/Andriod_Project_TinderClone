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
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityPhoneInputBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class PhoneInputActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityPhoneInputBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityPhoneInputBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        if (FirebaseAuth.getInstance().currentUser != null) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//
//        binding.btnPhoneInputContinue.setOnClickListener {
//            val inputPhoneNumber = binding.etPhoneInput.text.toString().trim()
//            if (inputPhoneNumber.isEmpty()) {
//                Toast.makeText(this, "Please enter your phone number", Toast.LENGTH_SHORT).show()
//            } else {
//                val phoneNumber = "+66$inputPhoneNumber"
//                val intent = Intent(this, OTPActivity::class.java)
//                intent.putExtra("phoneNumber", phoneNumber)
//                startActivity(intent)
//            }
//        }
//    }
//}
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class PhoneInputActivity : AppCompatActivity() {

    private lateinit var continueBtn: Button
    private lateinit var phoneNumberET: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var number: String
    private lateinit var mProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_input)

        init()
        continueBtn.setOnClickListener {
            number = phoneNumberET.text.trim().toString()
            if (number.isNotEmpty()) {
                if (number.length == 9) {
                    number = "+66$number"
                    mProgressBar.visibility = View.VISIBLE
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

                } else {
                    Toast.makeText(this, "Please Enter correct Number", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Enter Number", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun init() {
        continueBtn = findViewById(R.id.btnPhoneInputContinue)
        phoneNumberET = findViewById(R.id.etPhoneInput)
        auth = FirebaseAuth.getInstance()
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this , "Authenticate Successfully" , Toast.LENGTH_SHORT).show()
                    sendToMain()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
                mProgressBar.visibility = View.INVISIBLE
            }
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            }
            mProgressBar.visibility = View.VISIBLE
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            val intent = Intent(this@PhoneInputActivity, OTPActivity::class.java)
            intent.putExtra("OTP", verificationId)
            intent.putExtra("resendToken", token)
            intent.putExtra("phoneNumber", number)
            startActivity(intent)
            mProgressBar.visibility = View.INVISIBLE
        }
    }

    private fun sendToMain(){
        startActivity(Intent(this , MainActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

