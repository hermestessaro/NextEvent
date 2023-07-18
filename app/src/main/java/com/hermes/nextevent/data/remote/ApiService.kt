package com.hermes.nextevent.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private const val BASE_URL = "https://5f5a8f24d44d640016169133.mockapi.io/api/"
    val api: EventApi = getInstance().create(EventApi::class.java)

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}