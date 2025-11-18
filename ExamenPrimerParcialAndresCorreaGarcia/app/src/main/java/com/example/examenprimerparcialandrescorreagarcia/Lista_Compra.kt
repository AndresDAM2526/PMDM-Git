package com.example.examenprimerparcialandrescorreagarcia

import java.util.Date

class Lista_Compra(var fecha: Date) : Calculable {
    //Inicializo el arrayList
    private val productos_cesta = mutableListOf<Producto_cesta>()
    override fun calcularTotal(): Double {
        var importe_total = 0.0
        //importe_total=productos_cesta.sumOf{it.precio}
        productos_cesta.forEach { producto -> importe_total += producto.precio } //productos_cesta.forEach{importe_total+=it.precio}
        return importe_total
    }

    //Recorre la colección y comprueba si cada uno de ellos cumple el filtro que recibe. IMPORTANTE, el filtro se indica cuando se llama a la función
    fun filtrar_productosReducida(filtro: (p: Producto_cesta) -> Boolean): List<Producto_cesta> =
        productos_cesta.filter { filtro(it) }


    fun filtrar_productosForEach(filtro: (p: Producto_cesta) -> Boolean): List<Producto_cesta> {
        val lista_resultado = mutableListOf<Producto_cesta>()
        productos_cesta.forEach {
            if (filtro(it)) {
                lista_resultado.add(it)
            }
        }
        return lista_resultado
    }


    fun filtrar_productos(filtro: (p: Producto_cesta) -> Boolean): List<Producto_cesta> {
        //Recorro la lista de productos de la cesta
        //y compruebo por la función filtro si ese producto
        //se va a añadir a la lista resultado
        val lista_resultado = mutableListOf<Producto_cesta>()

        for (producto in productos_cesta) {
            if (filtro(producto)) {
                //Cumple el criterio del filtro , lo añado a una lista de resultado
                lista_resultado.add(producto)
            }
        }
        return lista_resultado
    }

    fun Agregar_Producto(productoCesta: Producto_cesta) {
        productos_cesta.add(productoCesta)
    }

    fun obtener_productos_cesta(): List<Producto_cesta> = productos_cesta.toList()
}