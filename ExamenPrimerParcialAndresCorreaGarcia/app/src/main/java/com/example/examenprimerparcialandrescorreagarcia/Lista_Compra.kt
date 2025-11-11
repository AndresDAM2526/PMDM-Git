package com.example.examenprimerparcialandrescorreagarcia

import java.util.Date

class Lista_Compra(fecha: Date, private val productos_cesta: MutableList<Producto_cesta>) : Calculable{
    override fun calcularTotal(): Double {
        productos_cesta.forEach {
            var sum: Double =0.0
             sum+=it.precio;
            return sum
        }
        return 0.0
    }

    /*fun filtrar_productos(
        filtro:(producto: Producto_cesta)-> Boolean{

        }
    ): List<Producto_cesta>

     */
    fun Agregar_Producto( productoCesta: Producto_cesta){
        productos_cesta.add(productoCesta)
    }

    fun obtener_Productos(): MutableList<Producto_cesta> {
        return productos_cesta
    }
}