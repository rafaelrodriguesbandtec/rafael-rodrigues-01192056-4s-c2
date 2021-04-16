package com.example.auauapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConexaoApi {
    companion object {
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io")
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()
        }
    }
}