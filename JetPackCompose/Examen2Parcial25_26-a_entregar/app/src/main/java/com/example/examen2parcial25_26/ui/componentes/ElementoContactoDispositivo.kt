package com.example.examen2parcial25_26.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examen2parcial25_26.modelo.ContactoDispositivo
import com.example.examen2parcial25_26.ui.theme.BlueBackground
import com.example.examen2parcial25_26.ui.theme.RedError
import com.example.examen2parcial25_26.ui.theme.elemento_contacto_dispositivo

@Composable
fun elemento_contacto_dispositivo(contacto: ContactoDispositivo, modifier: Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(elemento_contacto_dispositivo)
    ) {
        Row {
            Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp)) {
                Text(contacto.nombre)
                Spacer(modifier = Modifier.height(10.dp))
                if (contacto.telefono != null && contacto.telefono != "") {
                    Text(contacto.telefono)
                } else {
                    Text("Sin teléfono", fontWeight = FontWeight.Bold, color = RedError)
                }
            }
            if (contacto.email != null && contacto.email != "") {
                Text(contacto.email, modifier = Modifier.padding(120.dp))
            } else {
                Text(
                    "Sin Email",
                    fontWeight = FontWeight.Bold,
                    color = RedError,
                    modifier = Modifier.padding(20.dp)
                )
            }

        }
    }
}


@Preview
@Composable
fun mostrarElemento() {
    elemento_contacto_dispositivo(ContactoDispositivo("1", "Juan", "", ""), Modifier)
}