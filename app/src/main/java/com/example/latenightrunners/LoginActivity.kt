package com.example.latenightrunners

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val view: ActivityLoginBinding by lazy{ActivityLoginBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnLoginPh.setOnClickListener() {
            val intent = Intent(this, PhoneInputActivity::class.java)
            startActivity(intent)
        }
    }
}