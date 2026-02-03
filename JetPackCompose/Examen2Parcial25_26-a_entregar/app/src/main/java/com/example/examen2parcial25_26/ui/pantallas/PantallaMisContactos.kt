package com.example.examen2parcial25_26.ui.pantallas

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.componentes.elemento_contacto


@Composable
fun pantallaMisContactos(
    modifier: Modifier = Modifier,
    contactos: List<Contacto>,
    pulsarFoto: (Contacto) -> Unit
) {

    LazyColumn(modifier = modifier) {
        itemsIndexed(
            contactos,
            key = { indice, elemento -> elemento.hashCode() }) { indice, elemento ->
            elemento_contacto(elemento, pulsarFoto)
        }
    }
}