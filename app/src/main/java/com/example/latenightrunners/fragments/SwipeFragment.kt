package com.example.latenightrunners.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.latenightrunners.databinding.SwipeFragmentBinding

class SwipeFragment: Fragment() {
    private lateinit var view: SwipeFragmentBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        view = SwipeFragmentBinding.inflate(inflater, container, false)

        return view.root


    }


}