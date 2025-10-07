package com.example.a

class Motocicleta(mar:String,mod:String,an:Int,vel_max:Int):
    Vehiculo(mar,mod,an,vel_max) {
        var esDeportiva: Boolean=false
            set(value) {
                //Solo se asignara valor true si está dentro del rango de valores
                if(marca in "Suzuki,Honda"){
                    field=true
                }else{
                    field=false
                }
            }
    override fun acelerar()="La motocicleta $marca,esta acelerando a toda velocidad"

}