package com.example.examen2parcial25_26.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.theme.BlueSurface

@Composable
fun anadirContacto(guardar: (Contacto) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(BlueSurface)

    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = nombre,
                label = { Text("Nombre") },
                onValueChange = { nombre = it }
            )
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = telefono,
                label = { Text("Telefono") },
                onValueChange = { telefono = it }
            )
            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                value = email,
                label = { Text("Email") },
                onValueChange = { email = it }
            )
            Button(
                { guardar(Contacto(nombre, telefono, email, null)) },
                modifier = Modifier.padding(horizontal = 12.dp)
            ) { Text("Guardar contacto") }

        }
    }
}

@Preview
@Composable
fun mostrar() {
    anadirContacto({})
}