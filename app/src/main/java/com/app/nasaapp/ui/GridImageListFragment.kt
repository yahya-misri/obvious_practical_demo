package com.app.nasaapp.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.nasaapp.MainActivity
import com.app.nasaapp.R
import com.app.nasaapp.adapter.GridAdapter
import com.app.nasaapp.databinding.FragmentGridImageListBinding
import com.app.nasaapp.interfaces.adapterCallback
import com.app.nasaapp.model.PictureModel
import com.app.nasaapp.viewmodel.PictureViewModel
import com.app.nasaapp.viewmodel.PictureViewModelFactory


class GridImageListFragment : Fragment(), adapterCallback {

    private lateinit var viewModel: PictureViewModel
    lateinit var binding: FragmentGridImageListBinding
    lateinit var gridAdapter: GridAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGridImageListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModel()

        setRecyclerView()

        val metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
        val widthPixels = metrics.widthPixels
        gridAdapter.widthPixels = widthPixels




        viewModel.getAllPictures().observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                viewModel.getImages()
            } else {
                gridAdapter.setData(it as ArrayList<PictureModel>)
            }
        }
    }

    private fun setRecyclerView() {
        binding.rvRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        gridAdapter = GridAdapter(this)
        binding.rvRecyclerView.adapter = gridAdapter
    }

    private fun setViewModel() {

        val pictureViewModelFactory = PictureViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, pictureViewModelFactory)[PictureViewModel::class.java]
    }

    override fun itemClicked(view: ImageView , arrayList: ArrayList<PictureModel>, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("pictureModel" , arrayList)
        bundle.putInt("position" ,position)

        (requireActivity()as MainActivity).navController?.navigate(R.id.action_startFragment_to_picturePagerFragment , bundle)
    }
}