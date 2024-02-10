package com.example.latenightrunners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.latenightrunners.databinding.ActivityPinConfirmBinding

class pin_confirm : AppCompatActivity() {
    private val view:ActivityPinConfirmBinding by lazy { ActivityPinConfirmBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}