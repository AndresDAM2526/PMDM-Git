package com.example.practica2

import android.content.Context
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

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
    posiciones.forEach { palabraOculta[it] = caracter }
    return palabraOculta
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

fun mostrarNotificacion(context: Context, mensaje: String) {
    val dialog = AlertDialog.Builder(context)
        .setMessage(mensaje)
        .setPositiveButton("Aceptar", { dialog, which ->
            dialog.dismiss()
        })
        .create()
    dialog.show()
}

