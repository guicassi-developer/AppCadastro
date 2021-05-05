package com.example.testelogin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testelogin.service.repository.local.SecurityPreferences

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mSharedPreferences = SecurityPreferences(application)

    private val mSair = MutableLiveData<Boolean>(false)
    var sair: LiveData<Boolean> = mSair


    fun sair(){
        mSharedPreferences.remove("email")
        mSharedPreferences.remove("token")
        mSharedPreferences.remove("personKey")
        mSharedPreferences.remove("name")
        mSair.value = true
    }

}