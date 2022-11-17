package com.app.nasaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.nasaapp.R
import com.app.nasaapp.databinding.FragmentNasaImageListBinding


class NasaImageListFragment : Fragment() {

    lateinit var binding : FragmentNasaImageListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNasaImageListBinding.inflate(inflater)
        return binding.root
    }
}