package com.app.nasaapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.nasaapp.model.PictureModel
import com.app.nasaapp.ui.PictureDetailFragment

class PicturePagerAdapter(
    var fragment: Fragment,
    var picturesModel: ArrayList<PictureModel>,
    var pos: Int
) : FragmentStatePagerAdapter(fragment.childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getCount(): Int {
        return picturesModel.size
    }

    override fun getItem(position: Int): Fragment {
        return PictureDetailFragment.newInstance(picturesModel[position])
    }

//    override fun getItemCount(): Int {
//        return picturesModel.size
//    }

//    override fun createFragment(position: Int): Fragment {
//        return PictureDetailFragment.newInstance(picturesModel[pos])
//    }


}