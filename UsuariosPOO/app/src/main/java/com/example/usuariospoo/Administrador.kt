package com.example.usuariospoo

import java.util.Date

class Administrador(var log:String,var pass:String,var fechNac: Date,var em: String,var area:String): Usuario(log,pass,fechNac,em,) {
    override fun obtenerDescripcion(): String {
        return super.obtenerDescripcion()+"\nAdministrador del area ${this.area}"
    }
}