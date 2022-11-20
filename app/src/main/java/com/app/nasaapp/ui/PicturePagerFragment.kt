package com.app.nasaapp.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import androidx.viewpager2.widget.ViewPager2
import com.app.nasaapp.adapter.PicturePagerAdapter
import com.app.nasaapp.databinding.PicturePagerFragmentBinding
import com.app.nasaapp.model.PictureModel


class PicturePagerFragment :Fragment() {

    private lateinit var binding: PicturePagerFragmentBinding
    private  var position=0
    private  var allData=ArrayList<PictureModel>()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = PicturePagerFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        allData = arguments?.getSerializable("pictureModel") as ArrayList<PictureModel>
        position = arguments?.getInt("position",0)!!

        val adapter = PicturePagerAdapter(this,allData,position)

//        parentFragmentManager.beginTransaction().replace(R.id.rootContainer, PictureDetailFragment.newInstance(allData[0]))
//            .commit()
        binding.viewpager.adapter = adapter

        binding.viewpager.currentItem = position


    }
}