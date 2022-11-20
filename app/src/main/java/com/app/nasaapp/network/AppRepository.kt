package com.app.nasaapp.network

import android.content.Context
import android.util.Log
import com.app.nasaapp.R
import com.app.nasaapp.model.PicturesModel
import com.application.myapplication.network.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import java.io.*
import java.lang.reflect.Type

class AppRepository {

    val TAG = AppRepository::class.java.simpleName

    fun fetchImages(context :Context): List<PicturesModel> {

        val ip: InputStream = context.resources.openRawResource(R.raw.data)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(ip, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                ip.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val jsonString: String = writer.toString()
        Log.d(TAG, "JSON $jsonString")
        val gson = Gson()
        val listType: Type = object : TypeToken<List<PicturesModel?>?>() {}.type
        return gson.fromJson(jsonString, listType) as List<PicturesModel>


    }

}