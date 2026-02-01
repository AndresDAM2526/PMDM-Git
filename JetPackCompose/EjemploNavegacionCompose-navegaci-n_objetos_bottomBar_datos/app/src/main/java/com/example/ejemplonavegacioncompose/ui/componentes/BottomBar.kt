package com.example.ejemplonavegacioncompose.ui.componentes

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue


import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.ejemplonavegacioncompose.navegacion.Home
import com.example.ejemplonavegacioncompose.navegacion.Login
import com.example.ejemplonavegacioncompose.navegacion.Perfil


@Composable
fun BottomBar(navController: NavHostController) {
    //Lista de pantallas que se mostrarán en el BottomBar
    val items = listOf(
        BottomItem(
            Login, "Login",
            Icons.Default.Home
        ),
        BottomItem(Perfil, "Perfil", Icons.Default.Person)
    )
    //Esta función recoge de la pila la pantalla que esta en la cima de la pila
    //como un state
    //Cualquier cambio en la cima de la pila implica recomposición

    val entradaActual by navController.currentBackStackEntryAsState()

    //Recogemos como string la ruta
    val ruta_actual = entradaActual?.destination?.route




    NavigationBar {
        items.forEach { elemento ->

            NavigationBarItem(
                icon = { Icon(elemento.icono, null) },
                label = { Text(elemento.etiqueta) },
                //Si la ruta actual es igual a la ruta de la etiqueta el icono esta seleccionado
                selected = ruta_actual == elemento.ruta::class.qualifiedName,
                onClick = {
                    //Cuando hagamos click
                    navController.navigate(elemento.ruta)
                }
            )
        }
    }
}
