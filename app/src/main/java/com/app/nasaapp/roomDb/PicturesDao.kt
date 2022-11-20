package com.app.nasaapp.roomDb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.nasaapp.model.PicturesModel

@Dao
interface PicturesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictures(albums: List<PicturesModel>)

    @Query("select * from pictures")
    fun getAllPictures(): LiveData<List<PicturesModel>>

    @Delete
    fun deletePicture(picturesModel: PicturesModel)

}