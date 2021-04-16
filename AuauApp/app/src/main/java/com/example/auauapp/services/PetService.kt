package com.example.auauapp.services

import com.example.auauapp.models.Cachorro
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface PetService {
    @POST("/bandtec-api/cachorros")
    fun cadastrarPet(@Body cachorro:Cachorro) : Call<Cachorro>

    @GET("/bandtec-api/cachorros")
    fun listarCachorros(): Call<List<Cachorro>>

}