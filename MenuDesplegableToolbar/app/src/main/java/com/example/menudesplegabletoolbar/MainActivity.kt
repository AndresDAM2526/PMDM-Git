package com.example.menudesplegabletoolbar

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.menudesplegabletoolbar.ui.theme.MenuDesplegableToolbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MenuDesplegableToolbarTheme {
                Scaffold(
                    topBar = {
                        MiTopBar(modifier = Modifier.fillMaxSize()) { texto, opcion ->
                            //De esta manera se puede ejecutar un código diferente en función de la opción que haya marcado el usuario
                            when (opcion) {
                                1 -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }

                                2 -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }

                                3 -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }

                                4 -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }

                                5 -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }

                                6 -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }

                                else -> {
                                    Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Text(text = "Contenido", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiTopBar(modifier: Modifier, clic_opcion: (String, Int) -> Unit) {
    var mostrarMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }

        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.carpeta_flecha),
                    contentDescription = "Archivar"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Enviar"
                )
            }
            IconButton(onClick = { mostrarMenu = !mostrarMenu }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Enviar"
                )
            }
            DropdownMenu(
                expanded = mostrarMenu,
                onDismissRequest = { mostrarMenu = false }
            ) {

                DropdownMenuItem(
                    text = { Text("Responder a todos") },
                    onClick = {
                        clic_opcion("Responder a todos", 1)
                        mostrarMenu = false
                    }
                )


                DropdownMenuItem(
                    text = { Text("Reenviar") },
                    onClick = {
                        clic_opcion("Reenviar", 2)
                        mostrarMenu = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Destacar") },
                    onClick = {
                        clic_opcion("Destacar", 3)
                        mostrarMenu = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Imprimir") },
                    onClick = {
                        clic_opcion("Imprimir", 4)
                        mostrarMenu = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Marcar los mensajes como no leídos d..") },
                    onClick = {
                        clic_opcion("Marcar los mensajes como no leídos d..", 5)
                        mostrarMenu = false

                    }
                )
                DropdownMenuItem(
                    text = { Text("Bloquear a Diego de OpenWebinars") },
                    onClick = {
                        clic_opcion("Bloquear a Diego de OpenWebinars", 6)
                        mostrarMenu = false
                    }
                )
            }
        })

}

@Composable
fun GmailPopupMenu(
    modificador: Modifier = Modifier,
    mostrarMenu: Boolean,
    clickOpcion: (mensaje: String, opcion: Int) -> Unit
) {
    var mostrarMenu = mostrarMenu


}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MenuDesplegableToolbarTheme {

    }
}