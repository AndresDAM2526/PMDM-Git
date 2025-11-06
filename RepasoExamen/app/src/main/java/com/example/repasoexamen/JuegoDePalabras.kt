package com.example.repasoexamen

class JuegoDePalabras() {
    private var palabras = listOf<String>("Arrancar", "Patata", "Juego", "Pizarra")
    private val pistas = listOf<String>(
        "Faltan caracteres",
        "Cambio de vocal",
        "Posición del caracter",
        "Cambio de consonante"
    )
    var puntos = 2
        get() = field
        set(value) {
            field = value
        }

    constructor(palabras: ArrayList<String>):this() {
        this.palabras = palabras
    }

    fun obtener_Palabra(): String {
        return palabras.random()
    }

    fun obtener_pista(num: Int): String {
        return pistas.get(num)
    }
}