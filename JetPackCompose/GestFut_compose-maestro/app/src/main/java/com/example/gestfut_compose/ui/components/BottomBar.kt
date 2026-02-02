package com.example.gestfut_compose.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestfut_compose.R
import com.example.gestfut_compose.navegacion.Calendario
import com.example.gestfut_compose.navegacion.Clasificacion
import com.example.gestfut_compose.ui.theme.ColorPrimary
import com.example.gestfut_compose.ui.theme.IconosBarra


// Enum para representar las pantallas
enum class BottomNavItem(val icon: Int, val title: String, val ruta: Any) {
    CalendarioScreen(R.drawable.baseline_calendar_today_24, "CALENDARIO", Calendario),
    ClasificacionScreen(R.drawable.baseline_format_list_numbered_24, "CLASIFICACION", Clasificacion)
}

@Composable
fun mibottombar(
    navController: NavHostController,
    pantallaActual: (Any ) -> Unit
) {
    val entradaActual by navController.currentBackStackEntryAsState()
    val ruta_actual = entradaActual?.destination?.route
    NavigationBar(
        containerColor = ColorPrimary, // fondo como colorPrimary
        tonalElevation = 0.dp
    ) {
        BottomNavItem.values().forEach { item ->
            NavigationBarItem(
                selected = ruta_actual == item.ruta::class.qualifiedName,
                onClick = { pantallaActual(item.ruta) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = IconosBarra,
                    selectedTextColor = IconosBarra
                )
                )
        }
    }
}