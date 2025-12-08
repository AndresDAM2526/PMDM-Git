package com.example.practica2

import android.content.Context
import android.widget.FrameLayout
import android.widget.ImageView

fun ocultarPalabra(palabra: String): MutableList<Char> {
    var palabraOculta: MutableList<Char> = mutableListOf()
    for (i in palabra) {
        palabraOculta += '_'
    }
    return palabraOculta
}

fun letraEncontrada(caracter: Char, palabraOriginal: String): MutableList<Int> {
    var posiciones: MutableList<Int> = mutableListOf()
    for (i in palabraOriginal.indices) {
        if (palabraOriginal[i] == caracter) {
            posiciones.add(i)
        }
    }
    return posiciones
}

fun mostrarLetra(
    posiciones: MutableList<Int>,
    palabraOculta: MutableList<Char>,
    caracter: Char,
): MutableList<Char> {
        posiciones.forEach { palabraOculta[it]=caracter }
        return palabraOculta
}

