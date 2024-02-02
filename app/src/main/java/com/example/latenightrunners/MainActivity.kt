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

        view.btnLoginPh {
            val intent = Intent(this,GenderActivity::class.java)
            startActivity(intent)
        }
    }
}