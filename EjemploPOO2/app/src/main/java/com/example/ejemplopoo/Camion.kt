package com.example.ejemplopoo

class Camion(var mar:String,var mod:String,var ano:Int,var velMax:Int,var capacidadCarga:Double): Vehiculo(mar,mod,ano,velMax) {
    override fun acelerar() ="El camión $marca está acelerando con una carga de $capacidadCarga toneladas"
    }
