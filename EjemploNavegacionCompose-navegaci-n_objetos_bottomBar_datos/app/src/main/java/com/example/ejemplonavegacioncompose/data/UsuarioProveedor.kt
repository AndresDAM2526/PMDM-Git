package com.example.ejemplonavegacioncompose.data

import com.example.ejemplonavegacioncompose.model.Usuario

class UsuarioProveedor {
    companion object{
        val usuarios = mutableListOf<Usuario>(
            Usuario(
                nombre = "juan",
                password = "1234",
                foto = "avatar_juan"
            ),
            Usuario(
                nombre = "maria",
                password = "abcd",
                foto = "avatar_maria"
            ),
            Usuario(
                nombre = "carlos",
                password = "qwerty",
                foto = "avatar_carlos"
            ),
            Usuario(
                nombre = "laura",
                password = "pass123",
                foto = "avatar_laura"
            ))

    }
}