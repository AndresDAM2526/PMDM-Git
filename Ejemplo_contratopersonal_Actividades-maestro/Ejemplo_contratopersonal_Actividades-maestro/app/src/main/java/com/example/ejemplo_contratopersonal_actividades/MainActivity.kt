package com.example.ejemplo_contratopersonal_actividades

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    //Defino el launcher, registerForActivityResult retorna un objeto
    //ActivityResultLauncher parametrizado, en función del contrato que le pase
    //y se utiliza indirectamente para arrancar la acción que se defina dentro del
    //intent del contrato
    /*
    var milauncher=registerForActivityResult(Contrato_Cadenas(Segunda_Activity::class.java)){
        dato:String->
        texto.text=dato
    }

     */
    //resultado es un objeto de tipo ActivityResult
    var milauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            if (resultado.resultCode == 1) {
                var datos: Intent? = resultado.data
                var numero = datos?.getIntExtra("entero", 0)
                var cadena = datos?.getStringExtra("cadena")

                texto.text=cadena
            }
        }
    lateinit var texto: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        texto = findViewById(R.id.texto_principal)

        texto.setOnClickListener {
            val miintent= Intent(this, Segunda_Activity::class.java)
            miintent.putExtra("dato","Texto que aparece en la segunda actividad")
            //Arranco la 2ª actividad
            milauncher.launch(miintent)
        }

    }
}