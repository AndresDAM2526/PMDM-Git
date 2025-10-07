package com.example.a

abstract class Vehiculo(var marca:String,var modelo:String,var anio:Int,var velocidadMaxima:Int):Conducible{
    override fun arrancar()="El vehículo $marca ha arrancado"

    override fun detener()="El vehículo $marca se ha detenido"
    abstract fun acelerar(): String
}