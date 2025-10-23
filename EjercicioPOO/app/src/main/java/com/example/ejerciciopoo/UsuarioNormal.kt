package com.example.ejerciciopoo

import java.time.LocalDate
import java.util.Date

class UsuarioNormal(var log:String, var pass:String, var nac: LocalDate, var em:String,var nivelMembresia:Int): Usuario(log,pass,nac,em) {
    override fun obtenerDescripcion()=super.obtenerDescripcion()+"\nNivel membresía->${this.nivelMembresia}"

}