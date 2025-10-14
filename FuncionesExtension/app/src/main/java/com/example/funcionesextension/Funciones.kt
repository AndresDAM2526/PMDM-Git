package com.example.funcionesextension

import kotlin.text.iterator

class Funciones {
    companion object fun contarCaracters(cadena:String,c: Char): Int{
        var cont=0
        for(a in cadena){
            if(a==c){
                cont++
            }
        }
        return cont
    }
}