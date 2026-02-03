package com.example.examen2parcial25_26.ui.componentes

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.example.examen2parcial25_26.R
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.ui.theme.BlueSurface
import com.example.examen2parcial25_26.ui.theme.TextPrimary
import com.example.examen2parcial25_26.ui.theme.TextSecondary
import java.io.File


@Composable

fun elemento_contacto(contacto: Contacto, pulsarFoto: (Contacto) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = BlueSurface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {

            ImagenContactoDesdeRuta(contacto.foto, { pulsarFoto(contacto) })

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),

                ) {

                Text(
                    text = contacto.nombre,
                    color = TextPrimary,
                    fontSize = 18.sp,          // 🔹 Nombre más grande
                    fontWeight = FontWeight.SemiBold

                )

                Spacer(modifier = Modifier.height(4.dp)) // 🔹 Espacio entre textos

                Text(
                    text = contacto.telefono,
                    color = TextSecondary,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = contacto.email,
                    color = TextSecondary,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun elemento_contacto_preview() {
    elemento_contacto(Contacto("Luis", "98438323", "add@gmail.com", null), {})
}

/*Funcion para cargar una imagen desde una ruta interna de la app */
@Composable
fun ImagenContactoDesdeRuta(ruta: String?, pulsarFoto: (Contacto) -> Unit) {
    if (ruta != null) {
        Image(
            painter = rememberAsyncImagePainter(File(ruta)),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clickable { pulsarFoto },
        )
    } else {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clickable { pulsarFoto }
        )
    }
}


/*Funcion para generar una URI, se deberá invocar antes de lanzar
* la intent de capturar imagen */
fun crearArchivoImagen(context: Context): Uri {
    val nombreArchivo = "contacto_${System.currentTimeMillis()}.jpg"
    val file = File(context.filesDir, nombreArchivo)
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )
}
