package com.example.ejerciciopoo

import java.util.Date

class UsuarioNormal(var log:String, var pass:String, var nac: Date, var em:String,var nivelMembresia:Int): Usuario(log,pass,nac,em) {
    override fun obtenerDescripcion(): String{
        return super.obtenerDescripcion()+"\nNivel membresía->${this.nivelMembresia}"
    }
}