package com.app.nasaapp.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.nasaapp.model.PictureModel

@Dao
interface PicturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictures(albums: List<PictureModel>)

    @Query("select * from pictures")
    fun getAllPictures(): LiveData<List<PictureModel>>

    @Delete
    fun deletePicture(picturesModel: PictureModel)

}