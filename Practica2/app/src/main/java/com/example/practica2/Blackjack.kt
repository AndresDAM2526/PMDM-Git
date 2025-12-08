package com.example.practica2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica2.databinding.ActivityBlackjackBinding
import kotlin.random.Random

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
        val toolbar: Toolbar = miBindingBlackjack.toolbarBlackJack
        toolbar.setTitle("JUEGOS DE TABLERO")
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        iniciarJuego()

    }

    fun iniciarJuego() {

        miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
        miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE

        miBindingBlackjack.btJugar.setOnClickListener {
            miBindingBlackjack.btJugar.visibility = View.INVISIBLE
            miBindingBlackjack.btPlantarse.visibility = View.VISIBLE
            miBindingBlackjack.btPedirCarta.visibility = View.VISIBLE

            for (i in 1..2) {
                cartaGeneradaUsuario = generarCarta()
                anadirCartaUsuario(obtenerIndice(cartaGeneradaUsuario))
                contadorUsuario += obtenerValorCarta(cartaGeneradaUsuario)
            }
            miBindingBlackjack.tvPuntuacionUsuario.text = contadorUsuario.toString()
            if (contadorUsuario == 21) {
                Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show()
                miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
            }
            cartaGeneradaCrupier = generarCarta()
            anadirCartaCrupier(13)
            contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)
            cartaGeneradaCrupier = generarCarta()
            anadirCartaCrupier(obtenerIndice(cartaGeneradaCrupier))
            contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)

            //Si el crupier tiene as y 10, gana directamente--Comprobarlo

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
                while (contadorCrupier < 21) {
                    cartaGeneradaCrupier = generarCarta()
                    anadirCartaCrupier(obtenerIndice(cartaGeneradaCrupier))
                    if (cartaGeneradaCrupier.equals("AS") && (contadorCrupier + obtenerValorCarta(
                            cartaGeneradaCrupier
                        ) > 21)
                    ) contadorCrupier += 1
                    else contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)


                }
                Toast.makeText(this, "Perdiste", Toast.LENGTH_LONG).show()
                miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                miBindingBlackjack.btVolverAJugar.visibility=View.VISIBLE

            } else if (contadorUsuario == 21) {
                Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show()
                miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                miBindingBlackjack.btVolverAJugar.visibility=View.VISIBLE


            }
        }

        miBindingBlackjack.btPlantarse.setOnClickListener {
            while (contadorCrupier < 21) {
                cartaGeneradaCrupier = generarCarta()
                anadirCartaCrupier(obtenerIndice(cartaGeneradaCrupier))
                if (cartaGeneradaCrupier.equals("AS") && (contadorCrupier + obtenerValorCarta(
                        cartaGeneradaCrupier
                    ) > 21)
                ) contadorCrupier += 1
                else contadorCrupier += obtenerValorCarta(cartaGeneradaCrupier)

                miBindingBlackjack.tvPuntuacionCrupier.text = contadorCrupier.toString()
                if (contadorCrupier == 21) {
                    Toast.makeText(this, "Ganó el crupier", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    miBindingBlackjack.btVolverAJugar.visibility=View.VISIBLE

                    break
                } else if (contadorCrupier > 21) {
                    Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                    break
                }
                if (21 - contadorUsuario < 21 - contadorCrupier) {
                    Toast.makeText(this, "Ganaste", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE

                } else {
                    Toast.makeText(this, "Perdiste", Toast.LENGTH_LONG).show()
                    miBindingBlackjack.btPlantarse.visibility = View.INVISIBLE
                    miBindingBlackjack.btPedirCarta.visibility = View.INVISIBLE
                }
            }
            miBindingBlackjack.btVolverAJugar.visibility=View.VISIBLE
        }

        miBindingBlackjack.btSalir.setOnClickListener {
            val pantallaPrincipal = Intent(this, MainActivity::class.java)
            startActivity(pantallaPrincipal)
            finish()
        }
        miBindingBlackjack.btVolverAJugar.setOnClickListener { reiniciarJuego() }


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
        miBindingBlackjack.btJugar.visibility=View.VISIBLE


    }

    fun generarCarta(): String {
        var numRandom = Random.nextInt(13) + 1
        return when (numRandom) {
            1 -> "as"
            in 2..10 -> "carta_${numRandom.toString()}"
            11 -> "j"
            12 -> "q"
            13 -> "k"
            else -> ""
        }
    }

    fun obtenerValorCarta(carta: String): Int {
        return when (carta) {
            "as" -> 11
            "j", "q", "k" -> 10
            else -> {
                carta.removePrefix("carta_").toInt()
            }
        }
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

    fun obtenerIndice(nombreCarta: String): Int {
        return when (nombreCarta) {
            "as" -> 0
            "carta_2" -> 1
            "carta_3" -> 2
            "carta_4" -> 3
            "carta_5" -> 4
            "carta_6" -> 5
            "carta_7" -> 6
            "carta_8" -> 7
            "carta_9" -> 8
            "carta_10" -> 9
            "j" -> 10
            "q" -> 11
            "k" -> 12
            "parte_trasera" -> 13
            else -> 0
        }
    }
}