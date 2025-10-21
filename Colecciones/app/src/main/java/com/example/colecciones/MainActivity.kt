package com.example.colecciones

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

data class Persona(var nombre:String,var edad:Int)
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
        val miLista: List<Persona> = listOf(Persona("Juan",15), Persona("Andrea",19))
        miLista.forEach {
            Log.i("Listas","$it")
        }
        //Defino una lista mutable
        val miListaMutable: MutableList<Persona> = miLista as MutableList
        miListaMutable.add(Persona("Saray",20))
    }
}