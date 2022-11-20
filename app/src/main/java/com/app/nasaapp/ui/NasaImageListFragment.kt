package com.app.nasaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.nasaapp.R
import com.app.nasaapp.databinding.FragmentNasaImageListBinding
import com.app.nasaapp.viewmodel.PictureViewModel
import com.app.nasaapp.viewmodel.PictureViewModelFactory


class NasaImageListFragment : Fragment() {

    private lateinit var viewModel: PictureViewModel
    lateinit var binding : FragmentNasaImageListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNasaImageListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()
        binding.rvRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        gridAdapter = MainGridAdapter(requireContext(), this)
        binding.rvRecyclerView.adapter = gridAdapter

        viewModel.getAllPictures().observe(viewLifecycleOwner) {
            allPictures.clear()
            allPictures.addAll(it)
            setData()
        }
    }

    private fun setViewModel() {

        val pictureViewModelFactory = PictureViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, pictureViewModelFactory)[PictureViewModel::class.java]
    }
}