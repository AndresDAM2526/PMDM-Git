package com.example.ejemplospoo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btNavegar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<EditText>(R.id.edittext).setBackgroundColor(getColor(R.color.white))
        var imagen:ImageView=findViewById<ImageView>(R.id.imageView)
        imagen.cargarImagen("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
    //   mostrar_mensaje("Mensaje", Toast.LENGTH_LONG)

        //Referencio un objeto editText
        var mitexto=findViewById<EditText>(R.id.edittext)
        //Asocio un escuchador a ese editText a traves de
    // la función de extensión definida en Extensiones.kt
        mitexto.despues_cambio_texto { //se ponen llaves porque en la declaración de la función indica que recibe una función como parámetro

            mostrar_mensaje(it)
        }

        btNavegar=findViewById<Button>(R.id.btNavegar)
        btNavegar.setOnClickListener { //Para poder cambiar a la segunda actividad
            //Se crea un objeto intent
            val segundaPantalla= Intent(this, prueba2::class.java)
            startActivity(segundaPantalla)
        }


    }
}