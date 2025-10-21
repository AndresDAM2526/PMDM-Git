package com.example.cuestionario

enum class Dificultad{BAJA,MEDIA,ALTA}
data class PreguntaTexto (val textoPregunta:String,val respuesta:String,val dificultad: Dificultad){}

data class PreguntaTrueFalse(val textoPregunta: String,val respuesta:Boolean,val dificultad: Dificultad){}

data class PreguntaAritmetica(val textoPregunta: String,val respuesta: Int,val dificultad: Dificultad){}

//CLASE GENÉRICA QUE ENGLOBA TODAS LAS ANTERIORES
data class Pregunta<T>(val textoPregunta: String,val respuesta:T,val dificultad: Dificultad)