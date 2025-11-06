package com.example.repasoexamen

import kotlin.random.Random

fun String.transformar(
    descolocado: Boolean,
    fun_cambiar: (caracter: Char, pos: Int) -> Char
): String {
    var palabraModificada = ""
    var palabraFinal = ""
    //Conjunto que guarda las posiciones a cambiar
    if (descolocado) {
        //Descolocamos la palabra original
        //Conjunto que guarda las posiciones a cambiar
        var conjunto = mutableSetOf<Int>()
        var num_aleatorio: Int
        while (conjunto.size < this.length) {
            //Genero un numero aleatorio entre 0 y la longitud de la cadena -1
            num_aleatorio = Random.nextInt(0, this.length - 1)
        }
        //Descolocamos la palabra
        //Recorro las posiciones de la palabra
        var pos: Int
        for (pos in 0..this.length - 1) {
            palabraModificada = palabraModificada + this.get(conjunto.elementAt(pos)).toString()
        }

    } else {
        palabraModificada = this
    }
    //Transformar la palabra, se recorre la palabra y se invoca por cada caracter a la función transformada
    for (i in 0..this.length - 1) {
        palabraFinal = palabraModificada + fun_cambiar(palabraModificada.get(i), i).toString()
    }
    //Retornar la palabra
    return palabraModificada
}