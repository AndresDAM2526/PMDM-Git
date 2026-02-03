package com.example.examen2parcial25_26.navegacion

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.examen2parcial25_26.data.ProveedorContactoDispositivo
import com.example.examen2parcial25_26.modelo.Contacto
import com.example.examen2parcial25_26.modelo.ContactoDispositivo
import com.example.examen2parcial25_26.ui.pantallas.pantallaContactosDispositivos
import com.example.examen2parcial25_26.ui.pantallas.pantallaMisContactos
import com.example.examen2parcial25_26.viewmodel.ContactosViewModel


@Composable
fun miNavHost(
    modificador: Modifier = Modifier,
    control_navegacion: NavHostController,
    permisosConcedidos: Boolean,
    pulsarFoto: (Contacto) -> Unit,
    solicitarPermisos: () -> Unit,
    llamar: (ContactoDispositivo) -> Unit
) {
    var contexto = LocalContext.current
    val contactosViewModel: ContactosViewModel = viewModel()

    NavHost(navController = control_navegacion, startDestination = ContactosApp) {
        composable<ContactosApp> {
            pantallaMisContactos(modificador, contactosViewModel.contactos, pulsarFoto)

        }
        composable<ContactosSistema> {
            pantallaContactosDispositivos(
                modifier = modificador,
                permisosConcedidos,
                ProveedorContactoDispositivo.contactosDispositivo,
                solicitarPermisos,
                llamar
            )
        }
    }

}