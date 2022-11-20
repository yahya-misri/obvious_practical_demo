package com.app.nasaapp.network


import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApplicationApi {

    @GET("300")
    suspend fun fetchImages(): Response<ResponseBody>



}