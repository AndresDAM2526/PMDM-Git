package com.example.examen2parcial25_26

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.examen2parcial25_26.data.ProveedorContacto
import com.example.examen2parcial25_26.data.ProveedorContactoDispositivo
import com.example.examen2parcial25_26.navegacion.miNavHost
import com.example.examen2parcial25_26.ui.componentes.anadirContacto
import com.example.examen2parcial25_26.ui.componentes.miTopAppBar
import com.example.examen2parcial25_26.ui.componentes.mibottombar
import com.example.examen2parcial25_26.ui.theme.BlueAccent
import com.example.examen2parcial25_26.ui.theme.BlueOnPrimary
import com.example.examen2parcial25_26.ui.theme.Examen2Parcial25_26Theme
import com.example.examen2parcial25_26.viewmodel.ContactosViewModel
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Necesario para poder cargar de un fichero json
        ProveedorContacto.inicializar(this)
        //Necesario para cargar los contactos del dispositivo
        ProveedorContactoDispositivo.inicializar(this)
        //Paso los contactos de assets a FilesDir
        copiarContactosDesdeAssets(this)
        setContent {
            Examen2Parcial25_26Theme {
                pantallaPrincipal()
            }
        }
    }

    fun copiarContactosDesdeAssets(context: Context) {
        try {
            val archivoDestino = File(context.filesDir, "contactos.json")

            // Si ya existe, no hacemos nada
            if (archivoDestino.exists()) return

            // Abrimos el archivo desde assets
            val inputStream = context.assets.open("contactos.json")

            // Leemos todo el contenido
            val contenido = inputStream.bufferedReader().use { it.readText() }

            // Lo escribimos en filesDir
            archivoDestino.writeText(contenido)

            Log.i("copiarContactos", "contactos.json copiado a filesDir correctamente")

        } catch (e: Exception) {
            Log.e("copiarContactos", "Error copiando contactos: ${e.message}")
        }
    }


}

@Composable
fun pantallaPrincipal() {
    val contexto = LocalContext.current
    //Definimos el controlador de navegación
    val controlador_navegacion = rememberNavController()
    //Definimos el elemento seleccionado en el bottombar
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }
    var permisos =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { aceptado ->
            if (!aceptado) {
                Toast.makeText(contexto, "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
            }
        }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { exito ->
            if (exito && imagenUri != null) {
                bitmap =
                    BitmapFactory.decodeStream(contexto.contentResolver.openInputStream(imagenUri!!))
            }
        }
    val contactosViewModel: ContactosViewModel = viewModel()

    var mostrarDialog by remember { mutableStateOf(false) }
    var permisosConcedidosContactos by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = { miTopAppBar { contactosViewModel.guardarContactos() } },
        bottomBar = { mibottombar(controlador_navegacion) { controlador_navegacion.navigate(it) } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    mostrarDialog = true
                }, containerColor = BlueAccent, contentColor = BlueOnPrimary
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Añadir contacto"
                )
            }
        },

        floatingActionButtonPosition = FabPosition.End
    ) {
        miNavHost(
            Modifier.padding(it), controlador_navegacion, permisosConcedidosContactos, { contacto ->
                if (ContextCompat.checkSelfPermission(
                        contexto, Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val values = ContentValues().apply {
                        put(
                            MediaStore.Images.Media.DISPLAY_NAME,
                            "foto_${System.currentTimeMillis()}.jpg"
                        )

                        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

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
                    var contactoActualizado = contacto.copy(
                        contacto.nombre, contacto.telefono, contacto.email, uri.toString()
                    )
                    contactosViewModel.actualizarContacto(contacto, contactoActualizado)
                } else {
                    permisos.launch(Manifest.permission.CAMERA)
                }
            }, {
                if (ContextCompat.checkSelfPermission(
                        contexto, Manifest.permission.READ_CONTACTS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    permisosConcedidosContactos = true
                } else {
                    permisos.launch(Manifest.permission.READ_CONTACTS)
                }
            },
            { contactoDispositivo ->
                if (!contactoDispositivo.telefono!!.equals("") && !contactoDispositivo.telefono.isEmpty()) {
                    val intent = Intent(Intent.ACTION_CALL).apply {
                        data = Uri.parse("tel: ${contactoDispositivo.telefono}")
                    }
                    contexto.startActivity(intent)
                }

            })

    }
    if (mostrarDialog) {
        Dialog({ mostrarDialog = false }) {
            anadirContacto {
                contactosViewModel.añadirContacto(
                    it
                )
                mostrarDialog = false
            }
        }
    }
}



