package com.example.ejercicio1.navegacion

import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ejercicio1.ui.componentes.bottomNavigation
import com.example.ejercicio1.ui.componentes.topbar
import com.example.ejercicio1.ui.pantallas.MiDatePicker
import com.example.ejercicio1.ui.pantallas.ajustes
import com.example.ejercicio1.ui.pantallas.likesScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph() {
    val contexto = LocalContext.current
    val navController = rememberNavController()
    var textoUsuario by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    var fechaEnMillis by remember { mutableStateOf<Long?>(null) }
    var estado by remember { mutableStateOf(false) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var permisos =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { aceptado ->
            if (!aceptado) {
                Toast.makeText(contexto, "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
            }
        }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { exito ->
        //Devuelve true si se obtuvo la imagen correctamente y realmente
        //se almacena en la uri que se creo
        if (exito && imagenUri != null) {
            // Convertir Uri → Bitmap
            bitmap = BitmapFactory.decodeStream(
                contexto.contentResolver.openInputStream(imagenUri!!)
            )
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { bottomNavigation(modifier = Modifier.fillMaxSize(), navController) },
        topBar = { topbar() }) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = likes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<likes> {
                likesScreen(1, "String", aumentar = {}, reducir = {})
            }
            composable<fecha> {
                MiDatePicker(
                    modifier = Modifier.fillMaxSize(),
                    bitmap = bitmap,
                    pickerState = datePickerState,
                    selectedDateMillis = fechaEnMillis,
                    onDateChange = { fechaEnMillis = it })

            }
            composable<ajustes> {
                ajustes(
                    textoUsuario,
                    { textoUsuario = it },
                    estado,
                    { estado = it },
                    abrirCamara = {
                        val permisosConcedidos = ContextCompat.checkSelfPermission(
                            contexto,
                            android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                        if (permisosConcedidos) {
                            val values = ContentValues().apply {
                                put(//El nombre del fichero imagen
                                    MediaStore.Images.Media.DISPLAY_NAME,
                                    "foto_${System.currentTimeMillis()}.jpg"
                                )
                                //El tipo de fichero
                                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                                //La ubicación relativa sobre la zona donde almacena los ficheros media el dispositivo
                                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraCompose")

                            }
                            val uri = contexto.contentResolver.insert(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // se guarda en una unidad externa(memoria SD,etc). Si se elije internal, se guarda dentro del movil
                                values
                            )
                            uri?.let {
                                imagenUri = uri



                                launcher.launch(uri)

                            }
                        } else {
                            permisos.launch(android.Manifest.permission.CAMERA)
                        }

                    })
            }


        }
    }
}