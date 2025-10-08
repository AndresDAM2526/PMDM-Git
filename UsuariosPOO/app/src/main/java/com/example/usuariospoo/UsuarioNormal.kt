package com.example.usuariospoo

import java.util.Date

class UsuarioNormal(var log:String, var pass:String, var fechNac: Date, var em:String,var nivelMembresia: Int): Usuario(log,pass,fechNac,em) {
    override fun obtenerDescripcion(): String{
        return super.obtenerDescripcion()+"\nNivel membresía->${this.nivelMembresia}"
    }
}