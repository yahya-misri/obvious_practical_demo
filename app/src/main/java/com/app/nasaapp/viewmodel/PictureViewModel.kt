package com.app.nasaapp.viewmodel

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.nasaapp.base.BaseViewModel
import com.app.nasaapp.model.PictureModel
import com.app.nasaapp.network.AppRepository
import com.app.nasaapp.roomDb.NasaAppDb
import com.app.nasaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


class PictureViewModel(override val app: Application) :
    BaseViewModel(app) {

    val images = MutableLiveData<Resources<Bitmap>>()

    private val userRepository = AppRepository();

    var db: NasaAppDb = NasaAppDb.invoke(app.applicationContext)


    fun getAllPictures(): LiveData<List<PictureModel>> {

        return db.picturesDao().getAllPictures()
    }

    fun getImages(){
        viewModelScope.launch (Dispatchers.IO){
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