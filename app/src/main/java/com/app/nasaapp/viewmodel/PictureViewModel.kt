package com.app.nasaapp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.nasaapp.base.BaseViewModel
import com.app.nasaapp.model.PicturesModel
import com.app.nasaapp.network.AppRepository
import com.app.nasaapp.roomDb.NasaAppDb
import com.app.nasaapp.utils.Resources
import kotlinx.coroutines.launch
import java.io.IOException


class PictureViewModel(override val app: Application) :
    BaseViewModel(app) {

    val images = MutableLiveData<Resources<Bitmap>>()
    var placeHolders = MutableLiveData<List<PicturesModel>>()

    var pictureList = MutableLiveData<List<PicturesModel>>()

    private val userRepository = AppRepository();

    var db: NasaAppDb = NasaAppDb.invoke(app.applicationContext)

    init {
        .observe(this) {
            if (it.isNullOrEmpty()) {
                getImages()
            } else {
                pictureList.postValue(it)

            }
        }
    }

    fun getAllPictures(): LiveData<List<PicturesModel>> {

        return db.picturesDao().getAllPictures()
    }

    fun getImages(){
        viewModelScope.launch {
            _getImageFromRaw()
        }
    }

    private fun _getImageFromRaw() {

        images.postValue(Resources.Loading())

        try {

            val response = userRepository.fetchImages(app.applicationContext)

            db.picturesDao().insertPictures(response)

        } catch (t: Throwable) {
            when (t) {
                is IOException -> images.postValue(Resources.Error("Network Failure", null))
                else -> images.postValue(Resources.Error("Conversion Error ${t.message}", null))
            }
        }
    }


}