package com.example.ejlambda4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Defino una función de orden superior (tiene un parámetro que es otra función)
    fun filtrar_numero(listaNumeros: Array<Int>,func:(Int)-> Boolean): Array<Int>{
        //Declaro una lista vacia que va a almacenar
        //los valores de lista_nuemros que cumplen
        //el filtro fun
        var listaResultados=mutableListOf<Int>()
        /*
        Recorro la lista de numeros, es decir, el primer parámetro
        y filtro segunda la función func, los valores que
        cumplen la condición definida en esa función fun
         */
        for(elementos in listaNumeros){
            /*
            Añado a listaResultado s el elemento segun la función func es true
             */
            if(func(elementos)) {
                listaResultados.add(elementos)
            }
        }
        return listaResultados.toTypedArray()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}