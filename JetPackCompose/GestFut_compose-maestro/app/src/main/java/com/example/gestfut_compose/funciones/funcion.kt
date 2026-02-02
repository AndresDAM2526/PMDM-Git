package com.example.gestfut_compose.funciones

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.gestfut.data.EquipoProveedor

@Composable
fun obtenerIdEscudo(nombreEquipo: String): Int {
    val contexto = LocalContext.current
    val equipo = EquipoProveedor.equipos.find { it.nombre == nombreEquipo }
    val escudoNombre = equipo!!.escudo.replace("@drawable/", "")
    val id = contexto.resources.getIdentifier(escudoNombre, "drawable", contexto.packageName)
    return id
}