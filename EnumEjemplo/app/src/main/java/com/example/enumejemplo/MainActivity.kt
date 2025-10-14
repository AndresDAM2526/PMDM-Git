package com.example.enumejemplo

import android.graphics.Color
import android.media.tv.TvView
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

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
    lateinit var spTamanoFuente: Spinner
    lateinit var spColorFuente: Spinner
    lateinit var tvView: TextView
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
        this.tvView=findViewById<TextView>(R.id.tv)

        this.spColorFuente=findViewById<Spinner>(R.id.spColorFuente)
        this.spTamanoFuente=findViewById<Spinner>(R.id.spTamanioFuente)
        var miAdaptadorSpColor= ArrayAdapter<Colores>(this,android.R.layout.simple_spinner_item,Colores.values())
        miAdaptadorSpColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTamanoFuente.adapter=miAdaptadorSpColor

        var miAdaptadorSpinnerTexto= ArrayAdapter<Float>(this,android.R.layout.simple_spinner_item,arrayOf(12f,18f,20f))
        miAdaptadorSpinnerTexto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spColorFuente.adapter=miAdaptadorSpinnerTexto

        spColorFuente.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                findViewById<TextView>(R.id.tv).setTextColor(((parent as Spinner).selectedItem as Colores).retornar_Color())


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        //Configuro el evento de seleccionar un elemento
        //en el Spinner

        //Declaro un objeto de la case que implementa la interface
        //OnItemSelectedListener
        var listener= EscuchadorSpinner()
        //spTamanoFuente.onItemSelectedListener=listener    Ejemplo creando una clase
        spTamanoFuente.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{  //Ejemplo creado una clase anónima
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Aqui se pone el código que queremos que se ejecute cuando se cambia un elemento del spinner
                //(parent?.parent as View).setBackgroundColor(((parent as Spinner).selectedItem as Colores).retornar_Color())
                findViewById<TextView>(R.id.tv).textSize=(parent as Spinner).selectedItem as Float
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

}

