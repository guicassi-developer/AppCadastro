package com.example.testelogin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testelogin.service.listener.APIListener
import com.example.testelogin.service.listener.ValidationListener
import com.example.testelogin.service.repository.HeaderModel
import com.example.testelogin.service.repository.PersonRepository
import com.example.testelogin.service.repository.local.SecurityPreferences

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val mPersonRepository = PersonRepository(application)
    private val mSharedPreferences = SecurityPreferences(application)

    private val mLoggedUser = MutableLiveData<Boolean>()
    var loggedUser: LiveData<Boolean> = mLoggedUser

    private val mLogin = MutableLiveData<ValidationListener>()
    var login: LiveData<ValidationListener> = mLogin


    /**
     * Faz login usando API
     */
    fun doLogin(email: String, senha: String,checkBox: Boolean) {
        mPersonRepository.login(email, senha, object : APIListener {
            override fun onSuccess(model: HeaderModel) {
                if(checkBox) {
                    mSharedPreferences.store("email", email)
                    mSharedPreferences.store("token", model.token)
                    mSharedPreferences.store("personKey", model.personKey)
                    mSharedPreferences.store("name", model.name)
                }
                mLogin.value = ValidationListener()
            }

            override fun onFailure(message: String) {

                mLogin.value = ValidationListener(message)

            }

        })
    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {

        val email = mSharedPreferences.get("email")
        val senha = mSharedPreferences.get("personKey")

        val logged = (email != "" && senha != "")
        mLoggedUser.value = logged
    }

}