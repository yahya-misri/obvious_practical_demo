package com.app.nasaapp.interfaces

import android.widget.ImageView
import com.app.nasaapp.model.PictureModel

interface adapterCallback {

    fun itemClicked(view: ImageView, arrayList: ArrayList<PictureModel>,position:Int)

}