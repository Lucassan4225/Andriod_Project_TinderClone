package com.example.latenightrunners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityNameSetUpBinding

class NameSetUpActivity : AppCompatActivity() {
    private val view: ActivityNameSetUpBinding by lazy { ActivityNameSetUpBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)



    }
}