package com.example.examen2parcial25_26.ui.componentes

import androidx.compose.foundation.layout.size

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.examen2parcial25_26.R
import com.example.examen2parcial25_26.navegacion.ContactosApp


import com.example.examen2parcial25_26.ui.theme.BlueOnPrimary
import com.example.examen2parcial25_26.ui.theme.BluePrimary
import com.example.examen2parcial25_26.ui.theme.BlueSecondary


sealed class BottomNavItem(var ic: ImageVector?, val tit: String, val ruta: Any) {
    object MisContactos : BottomNavItem(null, "MisContactos", ContactosApp)
    object ContactosSistema : BottomNavItem(
        null,
        "Dispositivo",
        com.example.examen2parcial25_26.navegacion.ContactosSistema
    )
}

@Composable
fun mibottombar(
    navController: NavHostController,
    pantallaActual: (Any) -> Unit
) {
    val entradaActual by navController.currentBackStackEntryAsState()
    val ruta_actual = entradaActual?.destination?.route
    //Defino los elementos
    val elementos_barrainferior =
        listOf<BottomNavItem>(BottomNavItem.MisContactos, BottomNavItem.ContactosSistema)
    //Cambio el icono de los contactosSistema
    elementos_barrainferior[0].ic = ImageVector.vectorResource(R.drawable.person_svgrepo_com)
    elementos_barrainferior[1].ic =
        ImageVector.vectorResource(R.drawable.agenda_address_book_svgrepo_com)

    NavigationBar(
        containerColor = BlueSecondary,// fondo de la BottomBar
    ) {
        elementos_barrainferior.forEach { item ->
            NavigationBarItem(
                selected = ruta_actual == item.ruta::class.qualifiedName,
                onClick = { pantallaActual(item.ruta) },
                icon = { Icon(item.ic!!, contentDescription = item.tit, Modifier.size(36.dp)) },
                label = { Text(text = item.tit) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BluePrimary,
                    selectedTextColor = BluePrimary,
                    unselectedIconColor = BlueOnPrimary.copy(alpha = 0.5f),
                    unselectedTextColor = BlueOnPrimary.copy(alpha = 0.7f)
                )

            )
        }
    }
}
