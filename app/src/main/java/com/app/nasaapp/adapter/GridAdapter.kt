package com.app.nasaapp.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.nasaapp.databinding.RowMaingridLayoutBinding
import com.app.nasaapp.interfaces.adapterCallback
import com.app.nasaapp.model.PictureModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class GridAdapter(
    var itemCallback: adapterCallback
) : RecyclerView.Adapter<GridAdapter.GridHolder>() {


    private val TAG: String = GridAdapter::class.java.simpleName

    var widthPixels = 0
    var allPictures: ArrayList<PictureModel> = arrayListOf()



    class GridHolder(var rowMainGridLayoutBinding: RowMaingridLayoutBinding) :
        RecyclerView.ViewHolder(rowMainGridLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {

        val rowPhotoLayoutBinding = RowMaingridLayoutBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
        return GridHolder(rowPhotoLayoutBinding)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        val w = ((widthPixels) / 2)
        val params = FrameLayout.LayoutParams(
            w,
            w
        )
        holder.rowMainGridLayoutBinding.cardView.layoutParams = params
        bind(holder.rowMainGridLayoutBinding, position)

    }

    override fun getItemCount(): Int {
        return allPictures.size
    }

    fun setData( allPictures: ArrayList<PictureModel>){
        this.allPictures = allPictures
        notifyDataSetChanged()
    }

    private fun bind(rowMainGridLayoutBinding: RowMaingridLayoutBinding, position: Int) {
        rowMainGridLayoutBinding.image.transitionName = allPictures[position].title

        Glide.with(rowMainGridLayoutBinding.root)
            .load(allPictures[position].url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any,
                    target: Target<Drawable?>, isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }
            }).into(rowMainGridLayoutBinding.image)


        rowMainGridLayoutBinding.cardView.setOnClickListener {
            itemCallback.itemClicked(rowMainGridLayoutBinding.image, allPictures ,position)

        }

    }

    fun clearAll() {
        if (allPictures.isNotEmpty()) {
            allPictures.forEachIndexed { pos, it ->

                remove(it, pos)
            }
        }
    }

    private fun remove(picturesModel: PictureModel, pos: Int) {
        allPictures.remove(picturesModel)
        notifyItemRemoved(pos)
    }
}