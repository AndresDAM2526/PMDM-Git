package com.example.ejerciciopoo

import java.time.LocalDate
import java.util.Date

class Administrador(var log: String,var pass:String,var nac: LocalDate,var em:String,var area:String): Usuario(log,pass,nac,em) {
    override fun obtenerDescripcion(): String {
        return super.obtenerDescripcion()+"\nAdministrador del area ${this.area}"
    }
}