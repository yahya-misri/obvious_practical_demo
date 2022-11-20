package com.app.nasaapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.nasaapp.model.PictureModel
import com.app.nasaapp.ui.PictureDetailFragment

class PicturePagerAdapter(
    fm: FragmentManager,
    lifCycle: Lifecycle,
    var picturesModel: ArrayList<PictureModel>
) : FragmentStateAdapter(fm,lifCycle) {


    private val mFrgmentList = ArrayList<Fragment>()
    private val mFrgmentTitleList = ArrayList<String>()

    override fun getItemCount(): Int {
        return picturesModel.size
    }

    override fun createFragment(position: Int): Fragment {
        return PictureDetailFragment.newInstance(picturesModel[position])
    }

    fun addFragment(fragment: Fragment, title: String) {
        mFrgmentList.add(fragment)
        mFrgmentTitleList.add(title)
    }
}