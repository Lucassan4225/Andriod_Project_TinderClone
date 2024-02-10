package com.example.latenightrunners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val view: ActivityMainBinding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnLoginPh.setOnClickListener() {
            val intent = Intent(this,Phone_num_input::class.java)
            startActivity(intent)
        }
    }
}