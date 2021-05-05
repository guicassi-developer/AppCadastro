package com.example.testelogin.service.repository.remote

import com.example.testelogin.service.repository.HeaderModel
import retrofit2.Call
import retrofit2.http.*

interface PersonService {
    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("senha") senha: String
    ): Call<HeaderModel>

    @POST("cadastro")
    @FormUrlEncoded
    fun create(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("senha") senha: String
    ): Call<HeaderModel>

}