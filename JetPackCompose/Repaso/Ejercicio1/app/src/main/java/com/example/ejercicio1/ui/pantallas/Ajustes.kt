package com.example.ejercicio1.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejercicio1.ui.theme.Ejercicio1Theme

@Composable
fun ajustes(
    textoUsuario: String,
    texto: (String) -> Unit,
    estado: Boolean,
    modoOscuro: (Boolean) -> Unit,
    abrirCamara: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Text("Modo oscuro")
            Switch(estado, { modoOscuro(it) })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Text("Idioma")
            TextField(textoUsuario, texto)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Text("Tamaño del texto")
            Switch(false, {})
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = abrirCamara) { Text("Abrir camara") }
    }

}

