package com.app.nasaapp.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.app.nasaapp.R
import com.app.nasaapp.databinding.PictureDetailFragmentBinding
import com.app.nasaapp.model.PictureModel
import com.app.nasaapp.utils.OnSwipeTouchListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class PictureDetailFragment: Fragment()  {

    private lateinit var pictureDetailFragmentBinding: PictureDetailFragmentBinding
    private lateinit var PictureModel: PictureModel

    companion object{
        fun newInstance(pictureModel:PictureModel):PictureDetailFragment {
            val args = Bundle()
            args.putSerializable("pictureModel",pictureModel)
            val fragment = PictureDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pictureDetailFragmentBinding=PictureDetailFragmentBinding.inflate(layoutInflater)

        return pictureDetailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PictureModel= arguments?.getSerializable("pictureModel") as PictureModel


        setData()

    }


    private fun setData() {

        pictureDetailFragmentBinding.tvTitle.text= PictureModel.title
        pictureDetailFragmentBinding.tvCopyright.text= PictureModel.copyright
        pictureDetailFragmentBinding.tvDescription.text= PictureModel.explanation


        Glide.with(this)
            .load(PictureModel.url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    parentFragment!!.startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    parentFragment!!.startPostponedEnterTransition()
                    return false
                }
            })
            .into(pictureDetailFragmentBinding.imageDetail)
    }
}