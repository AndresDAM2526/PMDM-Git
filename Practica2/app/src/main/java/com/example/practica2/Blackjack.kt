package com.example.practica2

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.practica2.databinding.ActivityBlackjackBinding
import com.example.practica2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class Blackjack : AppCompatActivity() {
    private lateinit var miBindingBlackjack: ActivityBlackjackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        miBindingBlackjack = ActivityBlackjackBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBindingBlackjack.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = miBindingBlackjack.toolbarBlackJack
        toolbar.setTitle("JUEGOS DE TABLERO")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        iniciarJuego()

    }

    fun iniciarJuego() {
        var listaCartasUsuario: MutableList<String> = mutableListOf()
        var listaCartasCrupier: MutableList<String> = mutableListOf()
        var contadorUsuario: Int = 0
        var contadorCrupier: Int = 0
        var finPartida: Boolean = false
        var cartaGeneradaUsuario: String
        var cartaGeneradaCrupier: String
        miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
        miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
        miBindingBlackjack.btVolverAJugar.visibility = View.INVISIBLE
        miBindingBlackjack.layoutCrupier.visibility = View.INVISIBLE
        miBindingBlackjack.layoutUsuario.visibility = View.INVISIBLE
        miBindingBlackjack.btBlackJackIniciar.setOnClickListener {
            miBindingBlackjack.btBlackJackIniciar.visibility = View.INVISIBLE
            miBindingBlackjack.btPlantarse.visibility = View.VISIBLE
            miBindingBlackjack.btPedirCarta.visibility = View.VISIBLE
            miBindingBlackjack.layoutCrupier.visibility = View.VISIBLE
            miBindingBlackjack.layoutUsuario.visibility = View.VISIBLE
            for (i in 1..2) {
                cartaGeneradaUsuario = generarCarta()
                contadorUsuario += obtenerValorCarta(cartaGeneradaUsuario)
                listaCartasUsuario.add(cartaGeneradaUsuario)
                cartaGeneradaCrupier = generarCarta()
                contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)
                listaCartasCrupier.add(cartaGeneradaCrupier)
                Log.i("INFO", "${obtenerValorCarta(cartaGeneradaUsuario)}")
                miBindingBlackjack.tvPuntuacionCrupier.text = contadorCrupier.toString()
                miBindingBlackjack.tvPuntuacionUsuario.text = contadorUsuario.toString()

            }
            miBindingBlackjack.tvCartasC.text = listaCartasCrupier[0]
            miBindingBlackjack.tvCartasU.text = listaCartasUsuario.toString()

        }

        miBindingBlackjack.btPedirCarta.setOnClickListener {
            while (!finPartida) {
                cartaGeneradaUsuario = generarCarta()
                if (cartaGeneradaUsuario.equals("AS") && (contadorUsuario + obtenerValorCarta(
                        cartaGeneradaUsuario
                    ) > 21)
                ) {
                    contadorUsuario += 1
                } else {
                    contadorUsuario += obtenerValorCarta(cartaGeneradaUsuario)
                }

                listaCartasUsuario.add(cartaGeneradaUsuario)
                miBindingBlackjack.tvCartasU.text = listaCartasUsuario.toString()
                miBindingBlackjack.tvPuntuacionUsuario.text = contadorUsuario.toString()
                if (contadorUsuario > 21) {
                    while (contadorCrupier < 21) {
                        cartaGeneradaCrupier = generarCarta()
                        if (cartaGeneradaCrupier.equals("AS") && (contadorCrupier + obtenerValorCarta(
                                cartaGeneradaCrupier
                            ) > 21)
                        ) contadorCrupier += 1
                        else contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)
                        listaCartasCrupier.add(cartaGeneradaCrupier)
                        miBindingBlackjack.tvCartasC.text = listaCartasCrupier.toString()
                        miBindingBlackjack.tvPuntuacionCrupier.text = contadorCrupier.toString()
                    }
                    finPartida = true
                    break
                } else if (contadorUsuario == 21) {
                    Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                    finPartida = true
                    break
                }
            }
        }

        miBindingBlackjack.btPlantarse.setOnClickListener {
            while (contadorCrupier < 21) {
                cartaGeneradaCrupier = generarCarta()
                if (cartaGeneradaCrupier.equals("AS") && (contadorCrupier + obtenerValorCarta(
                        cartaGeneradaCrupier
                    ) > 21)
                ) contadorCrupier += 1
                else contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)
                listaCartasCrupier.add(cartaGeneradaCrupier)
                miBindingBlackjack.tvCartasC.text = listaCartasCrupier.toString()
                miBindingBlackjack.tvPuntuacionCrupier.text = contadorCrupier.toString()
                if (contadorCrupier == 21) {
                    Toast.makeText(this, "Ganó el crupier", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                    break
                } else if (contadorCrupier > 21) {
                    Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                    break
                }
            }
        }


        /*
        //Snackbar.make(miBindingBlackjack.root,"Hola", Snackbar.LENGTH_LONG).show()
        val dialog = AlertDialog.Builder(this).setMessage("Hola").create()
        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({ dialog.dismiss() }, 2000)
        val toast = Toast(this)
        toast.duration = Toast.LENGTH_SHORT

// Crear un TextView como contenido
        val textView = TextView(this).apply {
            text = "¡Carta pedida!"
            setBackgroundColor(Color.DKGRAY)
            setTextColor(Color.WHITE)
            setPadding(20, 20, 20, 20)
            textSize = 18f
            gravity = Gravity.CENTER
        }

        toast.view = textView
        toast.setGravity(Gravity.CENTER, 0, 0) // aparece en el centro
        toast.show()

         */

    }

    fun generarCarta(): String {
        var numRandom = Random.nextInt(13) + 1
        return when (numRandom) {
            1 -> "AS"
            in 2..10 -> numRandom.toString()
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> ""
        }
    }

    fun obtenerValorCarta(carta: String): Int {
        return when (carta) {
            "AS" -> 11
            "J", "Q", "K" -> 10
            else -> carta.toInt() ?: 0
        }
    }
}