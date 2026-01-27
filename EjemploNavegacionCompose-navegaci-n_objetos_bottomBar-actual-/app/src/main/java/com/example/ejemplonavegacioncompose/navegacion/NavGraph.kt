package com.example.ejemplonavegacioncompose.navegacion

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejemplonavegacioncompose.ui.componentes.BottomBar
import com.example.ejemplonavegacioncompose.ui.pantallas.AjustesScreen
import com.example.ejemplonavegacioncompose.ui.pantallas.HomeScreen
import com.example.ejemplonavegacioncompose.ui.pantallas.ProfileScreen

//Aqui se define el NavHost y
//toda la navegación
@Composable
fun NavGraph() {
    //Defino el el controlador de navegación
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier.padding(padding)
        ) {
            //Defino las rutas, en este caso como objetos
            composable<Home> {

                HomeScreen()
            }
            composable<Perfil> {
                ProfileScreen()
            }

            composable<Ajustes> {
                AjustesScreen()
            }
        }
    }
}
