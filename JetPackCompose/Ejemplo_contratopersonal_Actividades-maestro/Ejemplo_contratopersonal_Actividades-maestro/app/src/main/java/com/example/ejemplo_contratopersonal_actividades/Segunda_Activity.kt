package com.example.ejemplo_contratopersonal_actividades

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Segunda_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_segunda)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
   // Muestro en el textview de esta actividad el valor que viene de la actividad principal
        var texto=findViewById<TextView>(R.id.textView)
        texto.text=intent.getStringExtra("dato").toString()

        //Cuando pulse el texto retorno algo a la actividad principal,
        //Tengo que crear un intent que contenga un dato con la clave dato_retorno
        texto.setOnClickListener{
            var mi_intent= Intent()
            mi_intent.putExtra("cadena","Valor que se devuelve")
            setResult(1,mi_intent)
            finish()
        }

    }
}