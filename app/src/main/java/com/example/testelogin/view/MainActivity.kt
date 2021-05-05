package com.example.testelogin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testelogin.R
import com.example.testelogin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        button_sair.setOnClickListener(this)

        observer()

    }


    private fun observer(){
        mViewModel.sair.observe(this, Observer {
            if(it) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_sair) {
            mViewModel.sair()
        }
    }

}