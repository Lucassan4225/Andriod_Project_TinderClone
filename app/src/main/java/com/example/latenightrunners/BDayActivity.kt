package com.example.latenightrunners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityBdayBinding

class BDayActivity : AppCompatActivity() {
    private val view: ActivityBdayBinding by lazy { ActivityBdayBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)




    }
}