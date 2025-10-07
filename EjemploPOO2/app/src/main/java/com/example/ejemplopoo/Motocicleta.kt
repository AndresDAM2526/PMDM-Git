package com.example.ejemplopoo

class Motocicleta(var mar:String,var mod:String,var ano:Int,var velMax:Int,): Vehiculo(mar,mod,ano,velMax) {
    var esDeportiva: Boolean=false
        set(value) {
            if(mar in "Honda,Suzuki"){
                field=true
            }else{
                field=false
            }
        }
    override fun acelerar() ="La motocicleta $marca está acelerando a toda velocidad"
    }
