package com.example.navigationbuttonwallapop.ui.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomBar() {
    val items = listOf(
        BottomNavigationItem("Inicio", Icons.Outlined.Home),
        BottomNavigationItem("Favoritos", Icons.Outlined.FavoriteBorder),
        BottomNavigationItem("Vender", Icons.Outlined.AddCircle),
        BottomNavigationItem("Buzón", Icons.Outlined.Email),
        BottomNavigationItem("Tú", Icons.Outlined.Person),
    )
    NavigationBar {
        items.forEach {
            NavigationBarItem(
                selected = false,
                icon = { Icon(it.icono, contentDescription = null) },
                label = { Text(it.nombre) },
                onClick = {}
            )
        }
    }
}