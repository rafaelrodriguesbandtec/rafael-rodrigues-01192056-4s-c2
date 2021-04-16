package com.example.auauapp.models

import com.google.gson.annotations.SerializedName

data class Cachorro
(
    @SerializedName("raca")
val raca:String,
    @SerializedName("precoMedio")
val precoMedio:Int,
    @SerializedName("indicadoCriancas")
val indicadoCriancas:Boolean
)