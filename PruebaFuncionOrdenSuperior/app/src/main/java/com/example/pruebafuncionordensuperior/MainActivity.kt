package com.example.pruebafuncionordensuperior

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Generamos la funcion de alto nivel fol
    //Es una función de extensión
    fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
    ): R {
        var accumulator: R = initial
        for (element: T in this) {
            accumulator = combine(accumulator, element)
        }
        return accumulator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        //Declaro un list
        var lista=listOf<Int>(1,2,5,6,7)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Defino objeto Button para vincularlo con el boton
        var miboton=findViewById<Button>(R.id.btFold)
        miboton.setOnClickListener {
            //Invoco a la función con una lambda que sume los valores de la lista y mostramos el resultado en el textView
            var resultado=lista.fold(0,{i:Int,j: Int->
                Log.i("Información","valor de i:$i y valor de j:$j")
                i+j

            })
            findViewById<TextView>(R.id.textView).text="El resultado es $resultado"
        }
    }
}