package com.example.ejercicio2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun juego() {
        println("1.Configurar,2.Jugar,3.Salir")
        var opcionUsuario: Int = readln().toInt()
        var numeroUsuario: Int
        while (opcionUsuario != 0) {
            var numIntentos: Int = 5
            var numMax: Int = 10
            when (opcionUsuario) {
                1 -> {
                    println("Número de intentos:")
                    numIntentos = readln().toInt()
                    println("Número máximo:")
                    numMax = readln().toInt()
                }

                2 -> {
                    var numAleatorio: Int = generarNumAleatorio(numIntentos)
                    var contador: Int = 0
                    while (contador <= numIntentos) {
                        println("Introduzca un numero:")
                        numeroUsuario = readln().toInt()
                        if (numeroUsuario == numAleatorio) {
                            println("Has ganado!. Has necesitado $contador intentos")
                            if (numeroUsuario > numAleatorio) {
                                println("El número es menor")
                            } else {
                                println("El número es mayor")
                            }
                            break
                        } else {
                            println("Perdiste!. Intentos consumidos")
                            contador++
                            break
                        }

                    }
                }

                3 -> {
                    println("Saliendo del juego...")
                    break;
                }
            }
        }

    }

    private fun numeroMaximo(num: Int): Int {
        return num + 1
    }

    private fun generarNumAleatorio(num: Int): Int {
        var numAleatorio: Int
        var num: Int = numeroMaximo(num)
        numAleatorio = (num * Math.random()).toInt()
        return numAleatorio
    }


}