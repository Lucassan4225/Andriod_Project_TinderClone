package com.example.latenightrunners

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.latenightrunners.databinding.ActivityMainNavigationBinding
import com.example.latenightrunners.fragments.FragmentA
import com.example.latenightrunners.fragments.FragmentB
import com.example.latenightrunners.fragments.Profile

class MainNavigationActivity : AppCompatActivity() {
    private val view: ActivityMainNavigationBinding by lazy{ ActivityMainNavigationBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)

        view.bnvExample.setOnItemSelectedListener {
            when (it.itemId){
                R.id.item_home -> changeFragment(FragmentA())
                R.id.item_match->changeFragment(FragmentB())
                R.id.item_profile->changeFragment(Profile())
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