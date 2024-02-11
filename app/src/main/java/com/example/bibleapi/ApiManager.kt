package com.example.bibleapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://bible-go-api.rkeplin.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService : ApiService = retrofit.create(ApiService::class.java)
    }
}