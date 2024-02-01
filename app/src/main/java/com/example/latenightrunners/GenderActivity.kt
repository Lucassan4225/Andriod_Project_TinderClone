package com.example.latenightrunners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityGenderBinding

class GenderActivity : AppCompatActivity() {
    private val view: ActivityGenderBinding by lazy{ActivityGenderBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}