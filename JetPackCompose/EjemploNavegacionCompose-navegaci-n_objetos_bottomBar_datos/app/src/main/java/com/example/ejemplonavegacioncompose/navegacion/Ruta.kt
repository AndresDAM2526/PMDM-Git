package com.example.ejemplonavegacioncompose.navegacion

import kotlinx.serialization.Serializable

//Es necesario hacer la ruta serializable porque las convierte en String





//2ª Ruta
@Serializable
object Perfil

@Serializable
object Login

//Como vamos a pasar parametros a la ruta es necesario definirla como data class
@Serializable
data class Home(val usuario:String, val avatar:String)
