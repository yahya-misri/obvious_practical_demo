package com.application.myapplication.network


import com.app.nasaapp.network.ApplicationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {

    companion object{

        private val JSON_URL :String ="https://picsum.photos/"


        private val jsonRetrofit by lazy {

            val loggingInterceptor =HttpLoggingInterceptor()
            loggingInterceptor.level =HttpLoggingInterceptor.Level.BODY

            val client =OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(40, TimeUnit.SECONDS )
                .build()

            Retrofit.Builder().baseUrl(JSON_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build()
        }


        val jsonApi by lazy {
            jsonRetrofit.create(ApplicationApi::class.java)
        }
    }
}
