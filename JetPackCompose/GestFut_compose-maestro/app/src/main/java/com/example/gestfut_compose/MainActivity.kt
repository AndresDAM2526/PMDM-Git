package com.example.gestfut_compose

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestfut.data.Equipo
import com.example.gestfut.data.EquipoProveedor
import com.example.gestfut.data.PartidoProveedor
import com.example.gestfut_compose.navegacion.Calendario
import com.example.gestfut_compose.navegacion.Clasificacion
import com.example.gestfut_compose.ui.components.BottomNavItem
import com.example.gestfut_compose.ui.components.MiTopBar
import com.example.gestfut_compose.ui.components.infoEquipo
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
        EquipoProveedor.inicializar(this)
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
    var contexto = LocalContext.current
    val navController = rememberNavController()
    var mostrarDialogo by remember { mutableStateOf(false) }
    var equipoSeleccionado by remember { mutableStateOf<Equipo?>(null) }
    val jornadas = remember {
        PartidoProveedor.partidos.map { it.jornada }.distinct().sorted().map { it.toString() }
    }

    var jornadaSeleccionada by remember { mutableStateOf(jornadas.first()) }
    var partidosJornada = remember(jornadaSeleccionada) {
        PartidoProveedor.partidos.filter {
            it.jornada.toString().equals(jornadaSeleccionada)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            MiTopBar {
                val textoCompartir = partidosJornada.joinToString(separator = "\n") {
                    "${it.equipo_local} - ${it.equipo_visitante}"
                }
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "Jornada-$textoCompartir")
                }
                contexto.startActivity(Intent.createChooser(intent, "Compartir clasificación vía"))
            }
        },
        bottomBar = { mibottombar(navController) { navController.navigate(it) } },

        ) { paddingValues ->
        NavHost(navController = navController, startDestination = Calendario) {
            composable<Calendario> {
                pantallaCalendario(
                    modificador = Modifier.padding(paddingValues),
                    jornadas = jornadas,
                    selectedJornada = jornadaSeleccionada,
                    onJornadaSelected = { jornadaSeleccionada = it },
                    partidos = partidosJornada
                )
            }
            composable<Clasificacion> {
                clasificacion(
                    modifier = Modifier.padding(paddingValues),
                    EquipoProveedor.equipos,
                    { equipo ->
                        equipoSeleccionado = equipo
                        mostrarDialogo = true
                    })
            }

        }
        if (mostrarDialogo == true && equipoSeleccionado != null) {
            Dialog({ mostrarDialogo = false }) { infoEquipo(equipoSeleccionado!!) }
        }
    }
}


@Preview
@Composable
fun Pantalla_principal_preview() {
    Pantalla_principal()
}