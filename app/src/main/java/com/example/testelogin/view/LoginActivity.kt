package com.example.testelogin.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testelogin.R
import com.example.testelogin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Verifica se usuário está logado
        verifyLoggedUser()

        // Inicializa eventos
        setListeners()
        observe()


    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            handleLogin()
        } else if (v.id == R.id.text_register) {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    /**
     * Inicializa os eventos de click
     */
    private fun setListeners() {
        button_login.setOnClickListener(this)
        text_register.setOnClickListener(this)
    }

    /**
     * Verifica se usuário está logado
     */
    private fun verifyLoggedUser() {
        mViewModel.verifyLoggedUser()
    }

    /**
     * Observa ViewModel
     */
    private fun observe() {
        mViewModel.login.observe(this, Observer {
            if (it.success()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(applicationContext, it.failure(), Toast.LENGTH_SHORT).show()
            }
        })

        mViewModel.loggedUser.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        })
    }

    /**
     * Autentica usuário
     */
    private fun handleLogin() {
        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()
        val checkBox: Boolean = (checkBox.isChecked)

        mViewModel.doLogin(email, senha, checkBox)
    }


}

