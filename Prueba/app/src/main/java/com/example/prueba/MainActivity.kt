package com.example.prueba

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    fun demo(x: Any) {
        if (x is String) {  //hace una conversión y ya podemos unas los métodos de ese objeto,
            println(x.length)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var dato: String? = null
        var personas= Array<String>(3){"Juan"}
        var coches= IntArray(2)

        println(dato?.length)
        var l = dato?.length
            ?: 0 //si l datos es nulo devuelve 0, el primer ?. comprueba si es nulo, el segundo indica lo que se le asigna
        println(l)

        for(i in 6 downTo 0 step 2) //Recorre de 6 a 0 de 2 en 2
        for(personas in 0..3)

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