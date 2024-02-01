package com.example.latenightrunners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityNameSetUpBinding

class NameSetUpActivity : AppCompatActivity() {
    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)


        view.btnNext2.setOnClickListener {
            val intent = Intent(this,BDayActivity::class.java)
            startActivity(intent)
        }


    }
}