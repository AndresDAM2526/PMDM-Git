package com.example.enumejemplo

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Defino una clase que implementa la interface que gestiona
//el evento de seleccionar elementos en un spinner
class EscuchadorSpinner(): AdapterView.OnItemSelectedListener{
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        //Aqui se pone el código que queremos que se ejecute cuando se cambia un elemento del spinner
        (parent?.parent as View).setBackgroundColor(((parent as Spinner).selectedItem as Colores).retornar_Color())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}

enum class Colores{ROJO,VERDE,AZUL,NEGRO;  //Se pone ; para diferenciar los valores que puede tomar de la funciones propias de la clase

    //Definimos una función que nos retorna el objeto color en función del valor

    fun retornar_Color(): Int {
        var micolor:Int
        when(this){
            ROJO-> micolor=Color.RED
            AZUL-> micolor=Color.BLUE
            VERDE->micolor=Color.GREEN
            NEGRO->micolor=Color.BLACK
        }
        return micolor
    }
}

class MainActivity : AppCompatActivity() {
    lateinit var miSpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarSpinner()
    }


    private fun inicializarSpinner(){
        this.miSpinner=findViewById<Spinner>(R.id.spinner)
        var miAdaptador= ArrayAdapter<Colores>(this,android.R.layout.simple_spinner_item,Colores.values())
        miAdaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        miSpinner.adapter=miAdaptador

        //Configuro el evento de seleccionar un elemento
        //en el Spinner

        //Declaro un objeto de la case que implementa la interface
        //OnItemSelectedListener
        var listener= EscuchadorSpinner()
        miSpinner.onItemSelectedListener=listener
    }
    class Clase1{
        class Clase2{}
    }

    var miObtetoClase= Clase1.Clase2()
}

