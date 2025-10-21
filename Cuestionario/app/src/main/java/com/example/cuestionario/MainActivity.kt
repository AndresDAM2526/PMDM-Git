package com.example.cuestionario

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Instancio unas preguntas
        //Pregunta con respuesta String
        var miPreguntaString= Pregunta<String>("Qué se celebra el 1 de noviembre","Día de los Santos",
            Dificultad.BAJA)
        //Pregunta con respuesta Boolean
        var miPreguntaBoolean= Pregunta<Boolean>("El 7 es número primo",true, Dificultad.MEDIA)
        //Pregunta con respuesta Int
        var miPreguntaAritmetica= Pregunta<Int>("2+2",4, Dificultad.BAJA)
    }
}