package com.example.ejercicio1.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ejercicio1.navegacion.ajustes
import com.example.ejercicio1.navegacion.fecha
import com.example.ejercicio1.navegacion.likes

@Composable
fun bottomNavigation(modifier: Modifier, navController: NavHostController) {
    val entradaActual by navController.currentBackStackEntryAsState();
    val rutaActual = entradaActual?.destination?.route
    val items = listOf(
        BottomItem(likes, "Likes", Icons.Default.Favorite),
        BottomItem(fecha, "Fecha", Icons.Default.DateRange),
        BottomItem(ajustes, "Ajustes", Icons.Default.Settings)
    )

    NavigationBar {
        items.forEach {
            NavigationBarItem(
                icon = { Icon(imageVector = it.icono, contentDescription = null) },
                label = { Text(it.etiqueta) },
                selected = rutaActual == it.ruta::class.qualifiedName,
                onClick = {
                    navController.navigate(it.ruta)
                })
        }
    }
}

