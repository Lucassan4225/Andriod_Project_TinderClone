package com.example.latenightrunners.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.latenightrunners.databinding.FragmentABinding

class FragmentA: Fragment() {
    private lateinit var view: FragmentABinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = FragmentABinding.inflate(inflater, container, false)

        return view.root
    }
}