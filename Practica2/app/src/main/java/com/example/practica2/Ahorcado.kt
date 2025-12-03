package com.example.practica2

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.databinding.ActivityAhorcadoBinding

class Ahorcado : AppCompatActivity() {
    private lateinit var miBindingAhorcado: ActivityAhorcadoBinding
    var palabra: String="Ordenador"
    //private var palabras: List<String> = listOf("Ordenador","Palabra")
    override fun onCreate(savedInstanceState: Bundle?) {
        miBindingAhorcado = ActivityAhorcadoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBindingAhorcado.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = miBindingAhorcado.toolbarAhorcado
        toolbar.setTitle("Ahorcado")
        iniciarJuego()
    }

    fun iniciarJuego() {
        miBindingAhorcado.btJugar.setOnClickListener {
            miBindingAhorcado.etLetra.visibility = View.VISIBLE
            miBindingAhorcado.tvPalabraEscondida.visibility = View.VISIBLE
            miBindingAhorcado.btComprobar.visibility = View.VISIBLE
            miBindingAhorcado.btJugar.visibility = View.INVISIBLE
            miBindingAhorcado.tvPalabraEscondida.text=convertirPalabra(palabra).toString()
        }

    }
    fun convertirPalabra(palabra:String): List<Char>{
        var palabraOculta:List<Char> =listOf()
        for(i in palabra){
            palabraOculta+='_'
        }
        return palabraOculta
    }
}