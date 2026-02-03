package com.example.examen2parcial25_26.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.examen2parcial25_26.modelo.ContactoDispositivo
import com.example.examen2parcial25_26.ui.componentes.elemento_contacto_dispositivo

@Composable
fun pantallaContactosDispositivos(
    modifier: Modifier = Modifier,
    permisosConcedidos: Boolean,
    contactosTelefono: List<ContactoDispositivo>,
    solicitarPermisos: () -> Unit,
    llamar: (ContactoDispositivo) -> Unit,
) {
    if (permisosConcedidos == false) {
        Button(solicitarPermisos, modifier = modifier) { Text("Solicitar permisos contactos") }
    } else {
        LazyColumn(modifier = modifier) {
            itemsIndexed(
                contactosTelefono,
                key = { index, elemento -> elemento.hashCode() }) { indice, elemento ->
                elemento_contacto_dispositivo(
                    elemento,
                    modifier = Modifier.clickable { llamar(elemento) })
            }
        }
    }


}
