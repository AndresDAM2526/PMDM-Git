package com.example.ejercicio2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ejercicio2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding

    private lateinit var boton1: Button
    private lateinit var boton2: Button
    private lateinit var boton3: Button
    private lateinit var botonJugar: Button
    private lateinit var botonEnviar: Button

    private lateinit var texto1EditText: EditText
    private lateinit var texto2EditText: EditText

    private var numIntentos: Int = 5
    private var numMax: Int = 10


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        miBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarComponentes()
    }

    private fun inicializarComponentes() {
        this.boton1 = findViewById<Button>(R.id.boton1)
        this.boton2 = findViewById<Button>(R.id.boton2)
        this.boton3 = findViewById<Button>(R.id.boton3)
        this.botonJugar = findViewById<Button>(R.id.botonJugar)
        this.botonEnviar = findViewById<Button>(R.id.botonEnviar)
        this.texto1EditText = findViewById<EditText>(R.id.texto1EditText)
        this.texto2EditText = findViewById<EditText>(R.id.texto2EditText)
        texto1EditText.visibility = View.GONE
        texto2EditText.visibility = View.GONE
        botonJugar.visibility = View.GONE
        botonEnviar.visibility = View.GONE


        miBinding.boton1.setOnClickListener {
            boton1.visibility = View.INVISIBLE
            boton2.visibility = View.INVISIBLE
            boton3.visibility = View.INVISIBLE
            botonEnviar.visibility = View.VISIBLE
            texto1EditText.visibility = View.VISIBLE
            texto2EditText.visibility = View.VISIBLE
            miBinding.texto1EditText.hint = "Numero de intentos"
            miBinding.texto2EditText.hint = "Límite del numero aleatorio"
        }

        miBinding.boton2.setOnClickListener {
            boton1.visibility = View.GONE
            boton3.visibility = View.GONE
            boton2.visibility = View.GONE
            texto1EditText.visibility = View.VISIBLE
            botonEnviar.visibility = View.VISIBLE

        }

        miBinding.boton3.setOnClickListener {
            finish()
        }

        miBinding.botonJugar.setOnClickListener {
            var numUsuario = (texto1EditText.text.toString()).toInt()
            var numeroAleatorio = generarNumeroAleatorio(numMax)
            var contador = 0
            while (contador <= numIntentos) {
                if (numUsuario == numeroAleatorio) {
                    textoPantalla("Has ganado!.Has necesitado $contador intentos")
                    texto1EditText.text.clear()
                    texto2EditText.text.clear()
                    texto1EditText.visibility= View.GONE
                    texto2EditText.visibility= View.GONE
                    botonJugar.visibility=View.GONE
                    boton1.visibility= View.VISIBLE
                    boton2.visibility= View.VISIBLE
                    boton3.visibility= View.VISIBLE

                } else {
                    if (numUsuario > numeroAleatorio) {
                        textoPantalla("El numero oculto es menor $numeroAleatorio,$numIntentos,$numMax")
                    } else {
                        textoPantalla("El número oculto es mayor $numeroAleatorio,$numIntentos,$numMax")
                    }
                }
                contador++
            }

        }

        miBinding.botonEnviar.setOnClickListener {
            numIntentos =this.texto1EditText.text.toString().toInt()
            numMax = (this.texto2EditText.text.toString()).toInt()
            if (numIntentos == null && numMax == null) {
                textoPantalla("Introduzca valores correctos")
            }
            botonEnviar.visibility = View.GONE
            botonJugar.visibility = View.VISIBLE
            texto2EditText.visibility = View.INVISIBLE
            texto1EditText.text.clear()
            miBinding.texto1EditText.hint = "Introduzca un numero"

        }
    }


    private fun generarNumeroAleatorio(limiteSuperior: Int): Int {
        var numeroAleatorio = (Math.random() * limiteSuperior).toInt()
        return numeroAleatorio
    }

    private fun adivinarNumero(numeroUsuario: Int, numeroAleatorio: Int): Boolean {
        if (numeroUsuario == numeroAleatorio) {
            return true
        } else {
            return false
        }
    }

    private fun textoPantalla(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }


}