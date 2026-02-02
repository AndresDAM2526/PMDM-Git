package com.example.gestfut_compose.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.R

import com.example.gestfut_compose.ui.theme.IconosBarra
import com.example.gestfut_compose.ui.theme.PrimaryLight

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun infoEquipo(equipo: Equipo) {

    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(PrimaryLight)) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.ic_soccer),
                    contentDescription = "",
                    modifier = Modifier.size(75.dp)
                )
                GlideImage(
                    model = equipo.imagen_estadio,
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
            }
            Text("Equipo: ${equipo.nombre}")
            Text("Presidente: ${equipo.presidente}")
            Text("Ligas ganadas: ${equipo.ligas_ganadas}")
            Text("Nombre del estadio: ${equipo.estadio}")
        }


    }
}

@Preview
@Composable
fun mostrar() {
    infoEquipo(
        Equipo(
            "Real Madrid",
            1,
            2,
            3,
            "",
            "Florentino",
            1902,
            100,
            32,
            "Santiago Bernabeu",
            "https://upload.wikimedia.org/wikipedia/commons/e/eb/Estadio_Santiago_Bernab%C3%A9u_2017.jpg"
        ),
    )
}

