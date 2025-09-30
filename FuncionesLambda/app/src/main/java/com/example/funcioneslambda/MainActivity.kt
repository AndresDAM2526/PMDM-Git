package com.example.funcioneslambda

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        fun calcularSumaCadena(Cad:String): Int{

        }
        //Se crea una función de la clase String
        fun String.calcularSumaCadena():Int{

        }
        //Funcion como expresión lambda que sume dos numeros
        //esa función se la voy a asignar a una variable
        var miFuncionSuma:(Int,Int)-> Unit =
            { a: Int, b: Int -> Toast.makeText(this, "A+B=${a + b}", Toast.LENGTH_LONG).show() }

        var miFuncionProducto:(Int,Int)-> Int={
            a: Int,b:Int -> a*b
        }
        //función de orden superior
        /*
        fun setOnClickListener(funcionOnClick:(v: View)-> Unit){
            funcionOnClick(vista)
        }
        */


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Obtengo la referencia del textView
        var Mitext = findViewById<TextView>(R.id.miTextView)
        //Referencio un objeto de la clase Listener
        var miListener = Milistener()
        //Vinculo el evento CLick del textview al listener
        Mitext.setOnClickListener(miListener)

        //Otra forma sin lambda

        /*Mitext.setOnClickListener {
            View.OnClickListener() {
                fun onClick(v: View?) {
                    //Convierto el view a TextView
                    var miTextView = v as TextView

                    //Cambio el texto del TextView
                    miTextView.text = "Hola Mundo!"
                    Log.i("Msj", "El id del TextView es ${miTextView.id}")
                }
            }
        }*/

        //Con lambda
        Mitext.setOnClickListener { v: View ->

            var miTextView = v as TextView
            var producto=miFuncionProducto(4,5)
            //Cambio el texto del TextView
            miTextView.text = "Hola Mundo!"
            Log.i("Msj", "El id del TextView es ${miTextView.id}")
            miFuncionSuma(3,4)
            Toast.makeText(this,"A*B=${producto}", Toast.LENGTH_LONG).show()
        }

        /*
            Mitext.setOnClickListener {

            var miTextView= it as TextView  //hace referencia al parametro de la función sin tener que indicarlo

            //Cambio el texto del TextView
            miTextView.text="Hola Mundo!"
            Log.i("Msj","El id del TextView es ${miTextView.id}") }
         */
        var cadena="Hola 7 Adios 9"
        println(calcularSumaCadena(cadena))
        println(cadena.calcularSumaCadena())
    }
}

class Milistener : View.OnClickListener {
    override fun onClick(v: View?) {
        //Convierto el view a TextView
        var miTextView = v as TextView

        //Cambio el texto del TextView
        miTextView.text = "Hola Mundo!"
        Log.i("Msj", "El id del TextView es ${miTextView.id}")
    }
}