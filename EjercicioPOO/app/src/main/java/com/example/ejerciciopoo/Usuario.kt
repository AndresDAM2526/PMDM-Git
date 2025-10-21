package com.example.ejerciciopoo

import java.util.Calendar
import java.util.Date

open class Usuario( login: String, var password:String, fechaNac: Date?=null, var email: String="Prueba@gmail.com") {
    constructor(): this("Invitado","")
         var login:String= login
            get() = field
            set(value) {
                field=if(value.isBlank())"Desconocido" else value
            }
        var fechaNac: Date?= fechaNac
            get() = field
            set(value) {
                field=if(fechaNac!=null && mayoriaEdad(value))value else null
            }

        private fun mayoriaEdad(fechaNac: Date?): Boolean{
            val hoy= Calendar.getInstance();
            hoy.add(Calendar.YEAR,-18)
            val nacimiento= Calendar.getInstance()
            nacimiento.time=fechaNac
            return !nacimiento.after(hoy)
        }

    open fun obtenerDescripcion():String{
        return "Login->${this.login}"+"\nFecha nacimiento->${this.fechaNac}"+"\nEmail->${this.email}"
    }
    }
