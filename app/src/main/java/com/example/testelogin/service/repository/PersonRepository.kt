package com.example.testelogin.service.repository

import android.content.Context
import com.example.testelogin.R
import com.example.testelogin.service.constants.Constants
import com.example.testelogin.service.listener.APIListener
import com.example.testelogin.service.repository.remote.PersonService
import com.example.testelogin.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonRepository(val context: Context) {

    private val mRemote = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, senha: String, listener: APIListener){
        val call: Call<HeaderModel> = mRemote.login(email, senha)

        //Assincrona
        call.enqueue(object : Callback<HeaderModel> {
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if (response.code() != Constants.HTTP.SUCCESS) {
                    listener.onFailure(context.getString(R.string.invalidLogin))
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun create(name: String, email: String, senha: String, listener: APIListener){

        val call: Call<HeaderModel> = mRemote.create(name, email, senha)

        //Assincrona
        call.enqueue(object : Callback<HeaderModel> {
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if (response.code() != Constants.HTTP.SUCCESS) {
                    listener.onFailure(context.getString(R.string.invalidCadastro))
                } else {

                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }
}