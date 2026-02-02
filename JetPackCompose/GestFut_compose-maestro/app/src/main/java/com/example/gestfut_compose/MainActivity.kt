package com.example.gestfut_compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut_compose.navegacion.Calendario
import com.example.gestfut_compose.navegacion.Clasificacion
import com.example.gestfut_compose.ui.components.BottomNavItem
import com.example.gestfut_compose.ui.components.MiTopBar
import com.example.gestfut_compose.ui.components.mibottombar
import com.example.gestfut_compose.ui.pantallas.clasificacion
import com.example.gestfut_compose.ui.pantallas.pantallaCalendario
import com.example.gestfut_compose.ui.theme.GestFut_composeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Cargo los partidos
        PartidoProveedor.inicializar(this)
        setContent {
            GestFut_composeTheme {
                Pantalla_principal()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Pantalla_principal() {
    val navController = rememberNavController()
    //var pantalla_actual by remember { mutableStateOf(BottomNavItem.CalendarioScreen.ruta) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = { MiTopBar() },
        bottomBar = { mibottombar(navController) { navController.navigate(it) } },

        ) {
        paddingValues ->
        NavHost(navController = navController, startDestination = Calendario) {
            composable<Calendario> {
                pantallaCalendario(
                    modificador = Modifier.padding(paddingValues),
                    jornadas = listOf("0", "1", "2", "3", "4"),
                    selectedJornada = "0",
                    onJornadaSelected = {},
                    partidos = PartidoProveedor.partidos
                )
            }
            composable<Clasificacion> {
                clasificacion(modifier = Modifier.padding(paddingValues),)
            }
        }
    }
}


@Preview
@Composable
fun Pantalla_principal_preview() {
    Pantalla_principal()
}