package com.example.examen2parcial25_26.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Contacto(var nombre: String, var telefono: String, var email: String, var foto: String?)