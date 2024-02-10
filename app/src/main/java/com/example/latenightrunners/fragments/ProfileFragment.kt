package com.example.latenightrunners.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.latenightrunners.databinding.ProfileFragmentBinding

class ProfileFragment: Fragment() {
    private lateinit var view: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = ProfileFragmentBinding.inflate(inflater,container,false)
        return view.root
    }
}