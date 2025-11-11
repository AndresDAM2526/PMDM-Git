package com.example.examenprimerparcialandrescorreagarcia

enum class TipoProducto { COMIDA, BEBIDA, LIMPIEZA, HIGIENE, OTROS }
data class Producto_cesta(var nombre: String, var tipo: TipoProducto, var precio: Double) {

}
