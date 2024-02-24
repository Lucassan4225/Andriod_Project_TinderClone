package com.example.latenightrunners

//import android.os.Bundle
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

//
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
//        val intent = Intent(this, NameSetUpActivity::class.java)
//        startActivity(intent)
//        finish() // Finish the current activity to prevent going back to it
//    }
//
//    companion object {
//        private const val TAG = "OTPActivity"
//    }
//}
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        // Handle back press, prevent going back to OTP screen after successful verification
        // You can customize this behavior as needed
        Toast.makeText(this, "Back press disabled", Toast.LENGTH_SHORT).show()
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
                    } else {
                        // Handle other sign-in failures
                        Toast.makeText(this@OTPActivity, "Sign-in failed", Toast.LENGTH_SHORT).show()
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


//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.util.Log
//import android.view.View
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.FirebaseException
//import com.google.firebase.FirebaseTooManyRequestsException
//import com.google.firebase.auth.*
//import java.util.concurrent.TimeUnit
//
//class OTPActivity : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//    private lateinit var verifyBtn: Button
//    private lateinit var resendTV: TextView
//    private lateinit var inputOTP1: EditText
//    private lateinit var inputOTP2: EditText
//    private lateinit var inputOTP3: EditText
//    private lateinit var inputOTP4: EditText
//    private lateinit var inputOTP5: EditText
//    private lateinit var inputOTP6: EditText
//    private lateinit var progressBar: ProgressBar
//
//    private lateinit var OTP: String
//    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
//    private lateinit var phoneNumber: String
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_otpactivity)
//
//        OTP = intent.getStringExtra("OTP").toString()
//        resendToken = intent.getParcelableExtra("resendToken")!!
//        phoneNumber = intent.getStringExtra("phoneNumber")!!
//
//        initViews()
//        progressBar.visibility = View.INVISIBLE
//        setListeners()
//        resendOTPTvVisibility()
//    }
//
//    private fun initViews() {
//        auth = FirebaseAuth.getInstance()
//        verifyBtn = findViewById(R.id.btnOTPContinue)
//        resendTV = findViewById(R.id.btnResendCode)
//        inputOTP1 = findViewById(R.id.otpEditText1)
//        inputOTP2 = findViewById(R.id.otpEditText2)
//        inputOTP3 = findViewById(R.id.otpEditText3)
//        inputOTP4 = findViewById(R.id.otpEditText4)
//        inputOTP5 = findViewById(R.id.otpEditText5)
//        inputOTP6 = findViewById(R.id.otpEditText6)
//    }
//
//    private fun setListeners() {
//        resendTV.setOnClickListener {
//            resendVerificationCode()
//            resendOTPTvVisibility()
//        }
//
//        verifyBtn.setOnClickListener {
//            val typedOTP =
//                (inputOTP1.text.toString() + inputOTP2.text.toString() + inputOTP3.text.toString()
//                        + inputOTP4.text.toString() + inputOTP5.text.toString() + inputOTP6.text.toString())
//
//            if (typedOTP.isNotEmpty()) {
//                if (typedOTP.length == 6) {
//                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
//                        OTP, typedOTP
//                    )
//                    progressBar.visibility = View.VISIBLE
//                    signInWithPhoneAuthCredential(credential)
//                } else {
//                    Toast.makeText(this, "Please Enter Correct OTP", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(this, "Please Enter OTP", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun resendOTPTvVisibility() {
//        inputOTP1.setText("")
//        inputOTP2.setText("")
//        inputOTP3.setText("")
//        inputOTP4.setText("")
//        inputOTP5.setText("")
//        inputOTP6.setText("")
//        resendTV.visibility = View.INVISIBLE
//        resendTV.isEnabled = false
//
//        Handler(Looper.myLooper()!!).postDelayed(Runnable {
//            resendTV.visibility = View.VISIBLE
//            resendTV.isEnabled = true
//        }, 60000)
//    }
//
//    private fun resendVerificationCode() {
//        val options = PhoneAuthOptions.newBuilder(auth)
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setActivity(this)
//            .setCallbacks(callbacks)
//            .setForceResendingToken(resendToken)
//            .build()
//        PhoneAuthProvider.verifyPhoneNumber(options)
//    }
//
//    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//            signInWithPhoneAuthCredential(credential)
//        }
//
//        override fun onVerificationFailed(e: FirebaseException) {
//            if (e is FirebaseAuthInvalidCredentialsException) {
//                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
//            } else if (e is FirebaseTooManyRequestsException) {
//                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
//            }
//            progressBar.visibility = View.VISIBLE
//        }
//
//        override fun onCodeSent(
//            verificationId: String,
//            token: PhoneAuthProvider.ForceResendingToken
//        ) {
//            OTP = verificationId
//            resendToken = token
//        }
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(this, "Authenticate Successfully", Toast.LENGTH_SHORT).show()
//                    sendToMain()
//                } else {
//                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
//                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
//                        // The verification code entered was invalid
//                    }
//                }
//                progressBar.visibility = View.VISIBLE
//            }
//    }
//
//    private fun sendToMain() {
//        startActivity(Intent(this, MainActivity::class.java))
//    }
//}
//
