package com.example.practica2

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.practica2.databinding.ActivityBlackjackBinding
import com.example.practica2.databinding.ActivityMainBinding

class Blackjack : AppCompatActivity() {
    private lateinit var miBindingBlackjack: ActivityBlackjackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        miBindingBlackjack = ActivityBlackjackBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBindingBlackjack.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = miBindingBlackjack.toolbarBlackJack
        toolbar.setTitle("JUEGOS DE TABLERO")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        iniciarlizarComponentes()

    }
    fun iniciarlizarComponentes(){
        miBindingBlackjack.btPlantarse.visibility= View.INVISIBLE
        miBindingBlackjack.btPedirCarta.visibility= View.INVISIBLE
        miBindingBlackjack.layoutCrupier.visibility= View.INVISIBLE
        miBindingBlackjack.layoutUsuario.visibility= View.INVISIBLE
        miBindingBlackjack.btBlackJackIniciar.setOnClickListener {
            miBindingBlackjack.btBlackJackIniciar.visibility= View.INVISIBLE
            miBindingBlackjack.btPlantarse.visibility= View.VISIBLE
            miBindingBlackjack.btPedirCarta.visibility= View.VISIBLE
            miBindingBlackjack.layoutCrupier.visibility= View.VISIBLE
            miBindingBlackjack.layoutUsuario.visibility= View.VISIBLE
        }
    }
}