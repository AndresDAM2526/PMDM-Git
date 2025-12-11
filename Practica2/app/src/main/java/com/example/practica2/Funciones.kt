package com.example.practica2

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

//Función que crea una lista de Char que almacena tantos _ como letras tenga la palabra
fun ocultarPalabra(palabra: String): MutableList<Char> {
    var palabraOculta: MutableList<Char> = mutableListOf()
    for (i in palabra) {
        palabraOculta += '_'
    }
    return palabraOculta
}

//Función que comprueba si el Char que recibe como parámetro está dentro de la palabra y devuelve una lista con las posiciones donde se encuentra
fun letraEncontrada(caracter: Char, palabraOriginal: String): MutableList<Int> {
    var posiciones: MutableList<Int> = mutableListOf()
    for (i in palabraOriginal.indices) {
        if (palabraOriginal[i] == caracter) {
            posiciones.add(i)
        }
    }
    return posiciones
}
//Función que recibe las posiciones de la letra encontrada y el caracter por el que tiene que sustituir el _
fun mostrarLetra(
    posiciones: MutableList<Int>,
    palabraOculta: MutableList<Char>,
    caracter: Char,
): MutableList<Char> {
    posiciones.forEach { palabraOculta[it] = caracter }
    return palabraOculta
}


//Función para generar la carta
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

//Funcion para obtener el valor de la carta que genera la función generarCarta()
fun obtenerValorCarta(carta: String): Int {
    return when (carta) {
        "as" -> 11
        "j", "q", "k" -> 10
        else -> {
            carta.removePrefix("carta_").toInt()
        }
    }
}

//Función para obtenener el indice de la carta que se mostrará por pantalla
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

//Función para mostrar un mensaje por pantalla
fun mostrarNotificacion(context: Context, mensaje: String,aceptarPulsado:()-> Unit) {
    val dialog = AlertDialog.Builder(context)
        .setMessage(mensaje)
        .setPositiveButton("Aceptar", { dialog, which ->
            dialog.dismiss()
        })
        .setOnDismissListener { aceptarPulsado() }
        .create()
    dialog.show()
}



//Función para simular que el crupier se planta
fun crupierSePlanta(): Boolean {
    return Random.nextBoolean()
}

