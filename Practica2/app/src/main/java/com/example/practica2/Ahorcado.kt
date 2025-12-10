package com.example.practica2

import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.databinding.ActivityAhorcadoBinding

class Ahorcado : AppCompatActivity() {
    var numPalabra = 0
    var intentos = 6
    var intentosUsuario = 0
    lateinit var imagenesAhorcado: TypedArray
    var imagenActual = 0
    private lateinit var miBindingAhorcado: ActivityAhorcadoBinding
    var palabras: List<String> = listOf("ORDENADOR", "RATON", "VEHICULO", "VENTANA", "BARCO")
    var palabraOculta: MutableList<Char> = mutableListOf()

    var puntuacion: Int = 0
    lateinit var miReloj: CountDownTimer

    var minutos: Int = 3
    var segundos: Int = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        miBindingAhorcado = ActivityAhorcadoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBindingAhorcado.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fondo = miBindingAhorcado.main
        val colorFondo = ContextCompat.getColor(this, R.color.fondoAhorcado)
        fondo.setBackgroundColor(colorFondo)
        val toolbar: Toolbar = miBindingAhorcado.toolbarAhorcado
        toolbar.setTitle("Ahorcado")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.fondoToolbarAhorcado))
        imagenesAhorcado = resources.obtainTypedArray(R.array.imagenesAhorcado)
        iniciarJuego()
    }

    fun iniciarJuego() {

        miBindingAhorcado.tvIntentos.text = "Intentos: $intentos"
        miBindingAhorcado.btJugar.setOnClickListener {
            inicializarReloj(this)
            imagenActual = 0
            intentosUsuario = 0
            intentos = 5
            palabraOculta = palabras[numPalabra].map { '_' }.toMutableList()
            miBindingAhorcado.tvInicio.visibility = View.GONE
            miBindingAhorcado.tvPuntuacion.visibility = View.VISIBLE
            miBindingAhorcado.ivImagenesAhorcado.visibility = View.VISIBLE
            miBindingAhorcado.tvIntentos.visibility = View.VISIBLE
            miBindingAhorcado.etLetra.visibility = View.VISIBLE
            miBindingAhorcado.tvPalabraEscondida.visibility = View.VISIBLE
            miBindingAhorcado.btComprobar.visibility = View.VISIBLE
            miBindingAhorcado.btJugar.visibility = View.GONE
            miBindingAhorcado.tvPuntuacion.visibility = View.VISIBLE
            miBindingAhorcado.tvIntentos.visibility = View.VISIBLE
            miBindingAhorcado.tvPuntuacion.text = "Puntuación $puntuacion"
            miBindingAhorcado.tvPalabraEscondida.text =
                ocultarPalabra(palabras[numPalabra]).joinToString(" ")
            miBindingAhorcado.ivImagenesAhorcado.setImageResource(
                imagenesAhorcado.getResourceId(
                    0,
                    -1
                )
            )
        }

        miBindingAhorcado.btComprobar.setOnClickListener {

            if (miBindingAhorcado.etLetra.text.isEmpty()) {
                Toast.makeText(this, "Introduzca una letra", Toast.LENGTH_LONG).show()
            } else if (miBindingAhorcado.etLetra.text.length != 1) {
                Toast.makeText(this, "Solo puede introducir una letra", Toast.LENGTH_LONG)
                    .show()
            } else {
                var caracter = miBindingAhorcado.etLetra.text.toString().uppercase()
                var posiciones: MutableList<Int> =
                    letraEncontrada(caracter[0], palabras[numPalabra])
                if (posiciones.isNotEmpty()) {
                    palabraOculta = mostrarLetra(posiciones, palabraOculta, caracter[0])
                    miBindingAhorcado.tvPalabraEscondida.text = palabraOculta.joinToString(" ")
                    miBindingAhorcado.etLetra.text.clear()
                    puntuacion += 5
                    miBindingAhorcado.tvPuntuacion.text = "Puntuación: $puntuacion"
                } else {
                    if (puntuacion == 0) {
                        puntuacion = 0
                    } else {
                        puntuacion--
                    }

                    miBindingAhorcado.tvPuntuacion.text = "Puntuación: $puntuacion"
                    miBindingAhorcado.ivImagenesAhorcado.setImageResource(
                        imagenesAhorcado.getResourceId(
                            imagenActual,
                            -1
                        )
                    )
                    intentosUsuario++
                    imagenActual++
                    miBindingAhorcado.tvIntentos.text = "Intentos: ${intentos - intentosUsuario}"
                    miBindingAhorcado.etLetra.text.clear()
                }
            }

            if (intentosUsuario >= intentos) {
                Toast.makeText(this, "Perdiste", Toast.LENGTH_LONG).show()
                reiniciarJuego()

            }
            if (palabraOculta.joinToString("") == palabras[numPalabra]) {
                numPalabra++
                siguientePalabra(this)
            }
        }
        miBindingAhorcado.btSalir.setOnClickListener {
            val pantallaPrincipal = Intent(this, MainActivity::class.java)
            startActivity(pantallaPrincipal)
            finish()
            miReloj.cancel()
        }
    }

    fun reiniciarJuego() {
        // Ocultar botones y EditText de juego
        miBindingAhorcado.btComprobar.visibility = View.GONE
        miBindingAhorcado.etLetra.visibility = View.GONE
        miBindingAhorcado.tvPalabraEscondida.visibility = View.GONE
        miBindingAhorcado.tvIntentos.visibility = View.GONE
        miBindingAhorcado.ivImagenesAhorcado.visibility = View.GONE
        miBindingAhorcado.tvPuntuacion.visibility = View.GONE

        // Mostrar solo el botón Jugar
        miBindingAhorcado.btJugar.visibility = View.VISIBLE
    }

    fun siguientePalabra(context: Context) {
        if (numPalabra >= palabras.size) {
            mostrarNotificacion(context, "Terminaste el juego") {
                val pantallaPrincipal = Intent(this, MainActivity::class.java)
                startActivity(pantallaPrincipal)
                finish()
            }
            numPalabra = 0

        }

        palabraOculta = ocultarPalabra(palabras[numPalabra])
        intentosUsuario = 0
        miBindingAhorcado.ivImagenesAhorcado.setImageResource(
            imagenesAhorcado.getResourceId(
                0,
                -1
            )
        )
        miBindingAhorcado.tvPalabraEscondida.text = palabraOculta.joinToString(" ")
        miBindingAhorcado.tvIntentos.text = "Intentos: ${intentos - intentosUsuario}"
        miBindingAhorcado.etLetra.text.clear()
    }

    fun inicializarReloj(context: Context) {
        val sonidoFin = MediaPlayer.create(this, R.raw.finaltemporizador)
        miReloj = object : CountDownTimer(180000, 1000) {
            override fun onFinish() {
                sonidoFin.start()
                val pantallaPrincipal = Intent(context, MainActivity::class.java)
                startActivity(pantallaPrincipal)
                finish()
                miReloj.cancel()

            }

            override fun onTick(millisUntilFinished: Long) {
                segundos--

                if (segundos < 0) {
                    segundos = 59
                    minutos--
                }
                miBindingAhorcado.tvTemporizador.text = "$minutos:$segundos"
            }
        }
        miReloj.start()
    }


}