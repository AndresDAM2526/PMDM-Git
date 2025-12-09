package com.example.practica2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.databinding.ActivityBlackjackBinding

class Blackjack : AppCompatActivity() {
    var contadorUsuario: Int = 0
    var contadorCrupier: Int = 0
    var cartaGeneradaUsuario: String = ""
    var cartaGeneradaCrupier: String = ""
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
        val fondo = miBindingBlackjack.main
        val colorFondo = ContextCompat.getColor(this, R.color.fondoPantallaBlackJack)
        fondo.setBackgroundColor(colorFondo)
        val toolbar: Toolbar = miBindingBlackjack.toolbarBlackJack
        toolbar.setTitle("JUEGOS DE TABLERO")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        iniciarJuego()

    }

    fun iniciarJuego() {

        miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
        miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
        miBindingBlackjack.tvCartasCrupier.visibility = View.INVISIBLE
        miBindingBlackjack.tvTusCartas.visibility = View.INVISIBLE

        miBindingBlackjack.btJugar.setOnClickListener {
            miBindingBlackjack.flCartasUsuario.visibility = View.VISIBLE
            miBindingBlackjack.flCartasCrupier.visibility = View.VISIBLE
            miBindingBlackjack.btJugar.visibility = View.INVISIBLE
            miBindingBlackjack.btPlantarse.visibility = View.VISIBLE
            miBindingBlackjack.btPedirCarta.visibility = View.VISIBLE
            miBindingBlackjack.tvPuntuacionUsuario.visibility = View.VISIBLE

            for (i in 1..2) {
                cartaGeneradaUsuario = generarCarta()
                anadirCartaUsuario(obtenerIndice(cartaGeneradaUsuario))
                contadorUsuario += obtenerValorCarta(cartaGeneradaUsuario)
            }
            miBindingBlackjack.tvPuntuacionUsuario.text = contadorUsuario.toString()
            if (contadorUsuario == 21) {
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Ganaste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                }, 1000)

            } else if (contadorUsuario > 21) {
                mostrarNotificacion(this, "Te has pasado")
                miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
            }

            cartaGeneradaCrupier = generarCarta()
            anadirCartaCrupier(13)
            contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)
            cartaGeneradaCrupier = generarCarta()
            anadirCartaCrupier(obtenerIndice(cartaGeneradaCrupier))
            contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)


        }

        miBindingBlackjack.btPedirCarta.setOnClickListener {

            cartaGeneradaUsuario = generarCarta()
            anadirCartaUsuario(obtenerIndice(cartaGeneradaUsuario))
            if (cartaGeneradaUsuario.equals("AS") && (contadorUsuario + obtenerValorCarta(
                    cartaGeneradaUsuario
                ) > 21)
            ) {
                contadorUsuario += 1
            } else {
                contadorUsuario += obtenerValorCarta(cartaGeneradaUsuario)
            }
            miBindingBlackjack.tvPuntuacionUsuario.text = contadorUsuario.toString()

            if (contadorUsuario > 21) {

                cartaGeneradaCrupier = generarCarta()
                anadirCartaCrupier(obtenerIndice(cartaGeneradaCrupier))
                if (cartaGeneradaCrupier.equals("AS") && (contadorCrupier + obtenerValorCarta(
                        cartaGeneradaCrupier
                    ) > 21)
                ) contadorCrupier += 1
                else contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Perdiste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                }, 1500)


            } else if (contadorUsuario == 21) {
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Ganaste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                }, 1500)


            }
        }

        miBindingBlackjack.btPlantarse.setOnClickListener {
            cartaGeneradaCrupier = generarCarta()
            anadirCartaCrupier(obtenerIndice(cartaGeneradaCrupier))
            if (cartaGeneradaCrupier.equals("AS") && (contadorCrupier + obtenerValorCarta(
                    cartaGeneradaCrupier
                ) > 21)
            ) contadorCrupier += 1
            else contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)

            miBindingBlackjack.tvPuntuacionCrupier.text = contadorCrupier.toString()
            if (contadorCrupier == 21) {
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Perdiste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                }, 1500)


            } else if (contadorCrupier > 21) {
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Ganaste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                }, 1500)

            } else if (21 - contadorUsuario < 21 - contadorCrupier) {
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Ganaste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                }, 1500)


            } else {
                Handler(Looper.getMainLooper()).postDelayed({
                    mostrarNotificacion(this, "Perdiste")
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
                }, 1500)

            }

            miBindingBlackjack.btVolverAJugar.visibility = View.VISIBLE
        }

        miBindingBlackjack.btSalir.setOnClickListener {
            val pantallaPrincipal = Intent(this, MainActivity::class.java)
            startActivity(pantallaPrincipal)
            finish()
        }
        miBindingBlackjack.btVolverAJugar.setOnClickListener { reiniciarJuego() }


    }

    fun reiniciarJuego() {
        miBindingBlackjack.flCartasUsuario.removeAllViews()
        miBindingBlackjack.flCartasCrupier.removeAllViews()
        contadorCrupier = 0
        contadorUsuario = 0
        cartaGeneradaCrupier = ""
        cartaGeneradaUsuario = ""
        miBindingBlackjack.tvCartasCrupier.visibility = View.INVISIBLE
        miBindingBlackjack.tvTusCartas.visibility = View.INVISIBLE
        miBindingBlackjack.flCartasCrupier.visibility = View.INVISIBLE
        miBindingBlackjack.flCartasUsuario.visibility = View.INVISIBLE
        miBindingBlackjack.tvPuntuacionUsuario.visibility = View.INVISIBLE
        miBindingBlackjack.tvPuntuacionCrupier.visibility = View.INVISIBLE
        miBindingBlackjack.btVolverAJugar.visibility = View.INVISIBLE
        miBindingBlackjack.btJugar.visibility = View.VISIBLE


    }

    fun anadirCartaCrupier(indiceCarta: Int) {
        val cartas = resources.obtainTypedArray(R.array.imagenesCartas)
        val drawableId = cartas.getResourceId(indiceCarta, -1)
        val params = FrameLayout.LayoutParams(400, 400)
        val carta = ImageView(this)
        carta.layoutParams = params
        carta.translationX = (miBindingBlackjack.flCartasCrupier.childCount * 100).toFloat()
        carta.setImageResource(drawableId)
        miBindingBlackjack.flCartasCrupier.addView(carta)
    }

    fun anadirCartaUsuario(indiceCarta: Int) {
        val cartas = resources.obtainTypedArray(R.array.imagenesCartas)
        val drawableId = cartas.getResourceId(indiceCarta, -1)
        val params = FrameLayout.LayoutParams(400, 400)
        val carta = ImageView(this)
        carta.layoutParams = params
        carta.translationX = (miBindingBlackjack.flCartasUsuario.childCount * 100).toFloat()
        carta.setImageResource(drawableId)
        miBindingBlackjack.flCartasUsuario.addView(carta)
    }


}