package com.example.testelogin.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testelogin.service.constants.Constants
import com.example.testelogin.service.listener.APIListener
import com.example.testelogin.service.listener.ValidationListener
import com.example.testelogin.service.repository.HeaderModel
import com.example.testelogin.service.repository.PersonRepository
import com.example.testelogin.service.repository.local.SecurityPreferences
import java.util.*

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val mPersonRepository = PersonRepository(application)
    private val mSharedPreferences = SecurityPreferences(application)

    private val mCreate = MutableLiveData<ValidationListener>()
    var create: LiveData<ValidationListener> = mCreate

    fun create(name:String, email: String, senha: String) {
        mPersonRepository.create(name, email, senha,  object : APIListener {
            override fun onSuccess(model: HeaderModel) {
                mCreate.value = ValidationListener()
            }

            override fun onFailure(message: String) {
                mCreate.value = ValidationListener(message)
            }

        })
    }

}