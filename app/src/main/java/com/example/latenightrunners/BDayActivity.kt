package com.example.latenightrunners

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityBdayBinding

class BDayActivity : AppCompatActivity() {
    private val view: ActivityBdayBinding by lazy { ActivityBdayBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.btnLoginPh.setOnClickListener {
            val intent = Intent(this,MainNavigationActivity::class.java)
            startActivity(intent)
        }



    }
}