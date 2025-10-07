package com.example.a

class Camion(var mar:String,var mod:String,var an:Int,var vel_max:Int,var capacidadCarga:Double):
    Vehiculo(mar,mod,an,vel_max) {
         //Se define dentro de la propiedad de la clase y no en el constructor , para probar diferentes cosas
        //Constructor secundario para iniciarlizar capacidad de carga
        constructor(cap_Carga: Double):
                this("","",0,0, capacidadCarga = cap_Carga)

        override fun acelerar()="El camion $marca esta acelerando"+"con una capacidad de carga de $capacidadCarga"
        }
