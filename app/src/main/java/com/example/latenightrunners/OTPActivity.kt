package com.example.latenightrunners

import android.content.Intent
//import android.os.Bundle
import android.widget.Toast
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
//#---------------------------------------------------------------------
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityOtpactivityBinding
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthProvider
//
//class OTPActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityOtpactivityBinding
//    private lateinit var auth: FirebaseAuth
//    private lateinit var phoneNumber: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//        phoneNumber = intent.getStringExtra("+66"+"phoneNumber").toString()
//
//        binding.btnOTPContinue.setOnClickListener {
//            val otp = binding.etOTP.text.toString().trim()
//
//            if (otp.isEmpty()) {
//                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
//            } else {
//                val credential = PhoneAuthProvider.getCredential(phoneNumber, otp)
//                signInWithPhoneAuthCredential(credential)
//            }
//        }
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success
//                    val user = task.result?.user
//                    // Proceed to NameSetUpActivity
//                    navigateToNameSetUpActivity()
//                } else {
//                    // Sign in failed
//                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//    }
//
//    private fun navigateToNameSetUpActivity() {
//        val intent = Intent(this, NameSetUpActivity::class.java)
//        startActivity(intent)
//        finish() // Optional: finish the current activity if you don't want to go back to it
//    }
//}

//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityOtpactivityBinding
//import com.google.firebase.auth.FirebaseAuth
//
//class OTPActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityOtpactivityBinding
//    private lateinit var auth: FirebaseAuth
//    private lateinit var phoneNumber: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//
//        // Get the phone number from the intent
//        phoneNumber = intent.getStringExtra("phoneNumber").toString()
//
//        // Generate a random 6-digit OTP
//        val otp = generateRandomOtp()
//
//        // Display the OTP to the user
//        Toast.makeText(this, "The OTP code is: $otp", Toast.LENGTH_LONG).show()
//
//        binding.btnOTPContinue.setOnClickListener {
//            val enteredOtp = binding.etOTP.text.toString().trim()
//
//            // Check if the entered OTP matches the generated OTP
//            if (enteredOtp == otp) {
//                // Proceed to NameSetUpActivity
//                navigateToNameSetUpActivity()
//            } else {
//                // Display error message if OTP is incorrect
//                Toast.makeText(this, "Incorrect OTP", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun generateRandomOtp(): String {
//        // Generate a random 6-digit number
//        return (100000..999999).random().toString()
//    }
//
//    private fun navigateToNameSetUpActivity() {
//        val intent = Intent(this, NameSetUpActivity::class.java)
//        startActivity(intent)
//        // Optional: finish the current activity if you don't want to go back to it
//    }
//}

// official code
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.example.latenightrunners.databinding.ActivityOtpactivityBinding
//import com.google.firebase.FirebaseException
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
//import com.google.firebase.auth.PhoneAuthCredential
//import com.google.firebase.auth.PhoneAuthOptions
//import com.google.firebase.auth.PhoneAuthProvider
//import java.util.concurrent.TimeUnit
//
//class OTPActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityOtpactivityBinding
//    private lateinit var auth: FirebaseAuth
//    private lateinit var phoneNumber: String
//    private var verificationId: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//        phoneNumber = intent.getStringExtra("phoneNumber").toString()
//
//        binding.btnOTPContinue.setOnClickListener {
//            val otp = binding.etOTP.text.toString().trim()
//
//            if (otp.isEmpty()) {
//                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
//            } else {
//                val credential = PhoneAuthProvider.getCredential(verificationId!!, otp)
//                signInWithPhoneAuthCredential(credential)
//            }
//        }
//
//        startPhoneNumberVerification(phoneNumber)
//    }
//
//    private fun startPhoneNumberVerification(phoneNumber: String) {
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(callbacks)
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }
//
//    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//
//        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//            Log.d(TAG, "onVerificationCompleted:$credential")
//            signInWithPhoneAuthCredential(credential)
//        }
//
//        override fun onVerificationFailed(e: FirebaseException) {
//            Log.w(TAG, "onVerificationFailed", e)
//            if (e is FirebaseAuthInvalidCredentialsException) {
//                // Invalid request
//            } else {
//                // Show a message and update the UI
//            }
//        }
//
//        override fun onCodeSent(
//            verificationId: String,
//            token: PhoneAuthProvider.ForceResendingToken
//        ) {
//            Log.d(TAG, "onCodeSent:$verificationId")
//            this@OTPActivity.verificationId = verificationId
//        }
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "signInWithCredential:success")
//                    val user = task.result?.user
//                    // Proceed to NameSetUpActivity
//                    navigateToNameSetUpActivity()
//                } else {
//                    // Sign in failed, display a message and update the UI
//                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                        // The verification code entered was invalid
//                        Toast.makeText(this@OTPActivity, "Invalid OTP", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//    }
//
//    private fun navigateToNameSetUpActivity() {
//        // Replace NameSetUpActivity::class.java with the desired activity to navigate to
//        // For example, MainActivity::class.java
//        val intent = Intent(this, NameSetUpActivity::class.java)
//        startActivity(intent)
//    }
//
//    companion object {
//        private const val TAG = "OTPActivity"
//    }
//}


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityOtpactivityBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class OTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpactivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var phoneNumber: String
    private var verificationId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        phoneNumber = intent.getStringExtra("phoneNumber").toString()

        binding.btnOTPContinue.setOnClickListener {
            val otp = binding.etOTP.text.toString().trim()

            if (otp.isEmpty()) {
                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
            } else {
                val credential = PhoneAuthProvider.getCredential(verificationId!!, otp)
                signInWithPhoneAuthCredential(credential)
            }
        }

        startPhoneNumberVerification(phoneNumber)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.w(TAG, "onVerificationFailed", e)
            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else {
                // Show a message and update the UI
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            Log.d(TAG, "onCodeSent:$verificationId")
            this@OTPActivity.verificationId = verificationId
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    // Proceed to NameSetUpActivity
                    navigateToNameSetUpActivity()
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(this@OTPActivity, "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    private fun navigateToNameSetUpActivity() {
        val intent = Intent(this, NameSetUpActivity::class.java)
        startActivity(intent)
        finish() // Finish the current activity to prevent going back to it
    }

    companion object {
        private const val TAG = "OTPActivity"
    }
}
