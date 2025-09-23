package com.example.holamundo

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.holamundo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Creo el objeto binding
    private lateinit var mibinding: ActivityMainBinding
    private lateinit var miBoton: Button   //Es obligatorio iniciar las variables, con  lateinit-> se inicializa mas tarde || nombre objeto:tipo
    private lateinit var edit_text: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        //Invocación método clase padre
        super.onCreate(savedInstanceState)
        //Asignarle valor al objeto mibiding.vinculo la vista al objeto binding
        mibinding= ActivityMainBinding.inflate(layoutInflater)
        //Aplicación ocupa toda la pantalla
        enableEdgeToEdge()
        //Establecerque interfaz visual tiene esta actividad
        setContentView(mibinding.root)

        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarComponentes()


    }

    private fun inicializarComponentes() {
        //En este método inicializo los componentes visuales de mi layout
        //Vinculo mi boton con el boton del layuout
        this.miBoton=findViewById<Button>(R.id.miBoton)
        //Vinculo mi editText con el editText del layout
        this.edit_text=findViewById<EditText>(R.id.editTextText)

        //Definir codigo a la pulsación del boton
        this.miBoton.setOnClickListener {
            //Aqui metemos el código que se ejecutará cuando pulsemos el botón
            var miToast: Toast
            miToast= Toast.makeText(this,edit_text.text,Toast.LENGTH_LONG)
            miToast.show()
        }

        mibinding.miBoton.setOnClickListener {
            Toast.makeText(this,mibinding.editTextText.text, LENGTH_LONG)
        }


    }
}