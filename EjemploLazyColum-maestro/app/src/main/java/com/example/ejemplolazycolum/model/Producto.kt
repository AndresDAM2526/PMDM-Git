package com.example.ejemplolazycolum.model

import android.net.Uri

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: Uri
)
