package com.example.practica2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        miBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = miBinding.toolbarPrincipal
        toolbar.setTitle("JUEGOS DE TABLERO")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        iniciarlizarComponentes()
    }

    fun iniciarlizarComponentes() {
        miBinding.btBlackJack.setOnClickListener {
            val pantallaBlackJack = Intent(this, Blackjack::class.java)
            startActivity(pantallaBlackJack)
        }

        miBinding.btAhorcado.setOnClickListener {
            val pantallaAhorcado = Intent(this, Ahorcado::class.java)
            startActivity(pantallaAhorcado)
        }
    }
}