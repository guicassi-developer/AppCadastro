package com.example.testelogin.service.listener


import com.example.testelogin.service.repository.HeaderModel


interface APIListener {

    fun onSuccess(model : HeaderModel)
    fun onFailure(message: String)

}