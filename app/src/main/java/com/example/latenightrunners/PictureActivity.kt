package com.example.latenightrunners

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.latenightrunners.databinding.ActivityPictureBinding

class PictureActivity : AppCompatActivity() {
    private val view: ActivityPictureBinding by lazy { ActivityPictureBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
    }
}