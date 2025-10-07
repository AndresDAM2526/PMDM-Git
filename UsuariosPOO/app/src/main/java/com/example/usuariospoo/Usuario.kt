package com.example.usuariospoo

import java.util.Calendar
import java.util.Date

class Usuario( login:String,var password:String, fechaNac: Date?= null,var email:String="aaa@aa.es") {
    constructor():this("Invitado","")
    var login:String=login
        get()=field
        set(value) {
            if (login.isBlank()) "Desconocido" else value
        }
    private fun mayoriaDeEdad(fechaNac: Date){
        val nacimiento= Calendar.getInstance()
        nacimiento.time=fechaNac
    }
}