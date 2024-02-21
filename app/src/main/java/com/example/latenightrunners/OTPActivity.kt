package com.example.latenightrunners

//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityOtpactivityBinding
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import java.util.concurrent.TimeUnit
//
//class OTPActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityOtpactivityBinding
//    private lateinit var auth: FirebaseAuth
//    private lateinit var verificationId: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//
//        val phoneNumber = "+66" + intent.getStringExtra("number")
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                    signInWithPhoneAuthCredential(credential)
//                }
//
//                override fun onVerificationFailed(exception: FirebaseException) {
//                    Toast.makeText(this@OTPActivity, "Verification Failed $exception", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this@OTPActivity, NameSetUpActivity::class.java))
//                    finish()
//                }
//
//                override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
//                    super.onCodeSent(verificationId, token)
//                    this@OTPActivity.verificationId = verificationId
//                }
//            }).build()
//
//        PhoneAuthProvider.verifyPhoneNumber(options)
//
//        binding.btnOTPContinue.setOnClickListener {
//            val otp = binding.etOTP.text.toString().trim()
//            if (otp.isEmpty()) {
//                Toast.makeText(this, "Please enter the OTP", Toast.LENGTH_SHORT).show()
//            } else {
//                val credential = PhoneAuthProvider.getCredential(verificationId, otp)
//                signInWithPhoneAuthCredential(credential)
//            }
//        }
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    startActivity(Intent(this, NameSetUpActivity::class.java))
//                    finish()
//                } else {
//                    Toast.makeText(this, "Login Failed ${task.exception?.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//}

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityOtpactivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpactivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var phoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        phoneNumber = intent.getStringExtra("+66"+"phoneNumber").toString()

        binding.btnOTPContinue.setOnClickListener {
            val otp = binding.etOTP.text.toString().trim()

            if (otp.isEmpty()) {
                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
            } else {
                val credential = PhoneAuthProvider.getCredential(phoneNumber, otp)
                signInWithPhoneAuthCredential(credential)
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val user = task.result?.user
                    // Proceed to NameSetUpActivity
                    navigateToNameSetUpActivity()
                } else {
                    // Sign in failed
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToNameSetUpActivity() {
        val intent = Intent(this, NameSetUpActivity::class.java)
        startActivity(intent)
        finish() // Optional: finish the current activity if you don't want to go back to it
    }
}