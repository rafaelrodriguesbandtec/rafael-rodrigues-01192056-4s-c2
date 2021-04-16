package com.example.auauapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.auauapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun cadastro(view: View) {
        startActivity(Intent(baseContext,CadastrarCachorro::class.java));
    }
    fun listar(view: View) {
        startActivity(Intent(baseContext,ListarCachorros::class.java));
    }
}