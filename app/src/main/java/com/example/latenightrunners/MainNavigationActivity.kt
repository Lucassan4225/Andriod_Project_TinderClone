package com.example.latenightrunners

import MatchFragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.latenightrunners.databinding.ActivityMainNavigationBinding

import com.example.latenightrunners.fragments.ProfileFragment
import com.example.latenightrunners.fragments.SwipeFragment
import com.google.firebase.auth.FirebaseAuth

class MainNavigationActivity : AppCompatActivity() {
    private val view: ActivityMainNavigationBinding by lazy{ ActivityMainNavigationBinding.inflate(layoutInflater)}
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser == null) {
            startActivity(Intent(this, PhoneInputActivity::class.java))
            finish()
        }

        view.bnvExample.setOnItemSelectedListener {
            when (it.itemId){
                R.id.item_home -> changeFragment(SwipeFragment())
                R.id.item_match->changeFragment(MatchFragment())
                R.id.item_profile->changeFragment(ProfileFragment())
                else -> false
            }
        }
    }


    private fun changeFragment(fragment: Fragment): Boolean{
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcExample, fragment)
            .addToBackStack(fragment::class.java.name)
            .commit()

        return true
    }
}