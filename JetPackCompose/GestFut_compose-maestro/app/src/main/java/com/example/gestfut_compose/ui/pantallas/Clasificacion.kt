package com.example.gestfut_compose.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gestfut.data.Equipo
import com.example.gestfut_compose.ui.components.equipoItem
import com.example.gestfut_compose.ui.components.partidoItem

@Composable
fun clasificacion(modifier: Modifier, equipos: List<Equipo>, clickInformacion: (Equipo) -> Unit) {
    val clasificacionReal = equipos.sortedByDescending { (it.pg * 3) + (it.PE) }
    LazyColumn {
        items(equipos) { equipo ->
            equipoItem(equipo, modifier = Modifier.clickable { clickInformacion(equipo) })
        }
    }
}