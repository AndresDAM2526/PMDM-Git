package com.example.gestfut_compose.ui.components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.ui.theme.IconosBarra

@Composable
fun equipoItem(equipo: Equipo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(IconosBarra)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                Image(
                    painterResource(id = R.drawable.arrow_up_float),
                    contentDescription = "Logo Equipo"
                )
                Text(equipo.nombre)
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    equipoItem(Equipo("REal", 1, 2, 1, "@Drawable", "", 2001, 12, 1, "", ""))
}