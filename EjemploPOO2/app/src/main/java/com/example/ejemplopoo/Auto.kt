package com.example.ejemplopoo

class Auto(var mar:String,var mod:String,var ano:Int,var velMax:Int,var numPuertas:Int): Vehiculo(mar,mod,ano,velMax) {
    override fun acelerar() = "El auto $marca está acelerando"

}