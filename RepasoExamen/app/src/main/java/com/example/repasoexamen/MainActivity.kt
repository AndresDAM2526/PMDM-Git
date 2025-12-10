package com.example.repasoexamen

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.repasoexamen.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var miBinding: ActivityMainBinding
    lateinit var miReloj: CountDownTimer
     var minuto=3
     var segundos=59
    var objPalabras = JuegoDePalabras()

    override fun onCreate(savedInstanceState: Bundle?) {
        miBinding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(miBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarlizarComponentes()
        inicializarReloj()
    }

    fun iniciarlizarComponentes() {

        //Definimos el listener para el botón de Jugar
        miBinding.btJugar.setOnClickListener {
            miBinding.etPalabra.isEnabled = true
            miBinding.btComprobar.isEnabled = true
            miBinding.btJugar.isEnabled = false
            miBinding.iv.visibility = View.INVISIBLE
            //Mostramos la palabra transformada
            //Generamos un numero aleatorio entre 0 y 3 para definir el modo de transformar la palabra
            var num = Random.nextInt(0, 3)
            //Mostramos la pista
            miBinding.tvPista.text = objPalabras.obtener_pista(num)
            var palabraCambiadda: String = ""
            when (num) {
                //Transformación faltan caracteres
                0 -> palabraCambiadda =
                    objPalabras.obtener_Palabra().transformar(Random.nextBoolean()) { c, pos ->
                        if (Random.nextBoolean()) {
                            '_'
                        } else {
                            c
                        }
                    }

                1 -> palabraCambiadda = objPalabras.obtener_Palabra().transformar(true) { c, pos ->
                    if (c == 'a') 'e'
                    else if (c == 'e') 'i'
                    else if (c == 'i') 'o'
                    else if (c == 'o') 'u'
                    else if (c == 'u') 'a'
                    else
                        c
                }

                2 -> palabraCambiadda = objPalabras.obtener_Palabra().transformar(false) { c, pos ->
                    if (pos % 2 == 0) {
                        (c.lowercaseChar().code - 'a'.code + 1) as Char
                    } else {
                        c
                    }
                }
                //Sustituit letra por la anterior
            }
        }
    }


    fun inicializarReloj(){
        miReloj=object : CountDownTimer(1800000,1000){
            override fun onFinish() {
                TODO("Not yet implemented")
            }

            override fun onTick(millisUntilFinished: Long) {
                segundos--
                if(segundos<0){
                    segundos=50
                    minuto--
                }
            }
        }
    }
}