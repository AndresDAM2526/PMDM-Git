package com.example.ejemplolazycolum

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.ejemplolazycolum.model.Producto
import com.example.ejemplolazycolum.ui.theme.EjemploLazyColumTheme
import com.example.ejemplolazycolum.ui.theme.TopBarColor

//La descripción de lo que se he hecho está dentro del fichero README.md

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploLazyColumTheme {
                var lista2 = remember {
                    mutableStateListOf<Producto>(

                    )
                }
                var mostrarDialog by remember { mutableStateOf(false) }
                ListaCompraScreen(lista2, { mostrarDialog = true })
                if (mostrarDialog) {
                    anadirProductoDialog(
                        { mostrarDialog = false },
                        { nombreProducto, descripcionProducto, precioProducto, imagenUri ->

                            var producto = Producto(
                                1,
                                nombreProducto,
                                descripcionProducto,
                                precioProducto.toDouble(),
                                imagenUri
                            )
                            lista2.add(producto)
                            mostrarDialog = false
                        },
                        {})
                }

            }
        }
    }
}


@Composable
fun ListaCompraScreen(productos: List<Producto>, onAgregarClick: () -> Unit) {
    Scaffold(
        topBar = { ListaCompraTopBar() },
        floatingActionButton = { AgregarFab(onClick = onAgregarClick) }) { paddingValues ->
        ListaCompra(
            productos = productos,
            modifier = Modifier.padding(paddingValues) // evita superposición con TopBar y FAB
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompraTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "ListaCompra") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = TopBarColor, titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun AgregarFab(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = TopBarColor,            // 👈 mismo color que la TopAppBar
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_add_24),
            contentDescription = "Agregar"
        )
    }
}

@Composable
fun anadirProductoDialog(
    onDismis: () -> Unit,
    guardar: (String, String, String, Uri) -> Unit,
    foto: () -> Unit,
) {
    var nombre_editText by remember { mutableStateOf("") }
    var descripcion_editText by remember { mutableStateOf("") }
    var precio_editText by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            imagenUri = uri

        }

    Dialog(onDismis) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
        ) {
            Column {
                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    value = nombre_editText,
                    onValueChange = { nombre_editText = it },
                    label = { Text("Nombre") })
                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    value = descripcion_editText,
                    onValueChange = { descripcion_editText = it },
                    label = { Text("Descripción") })
                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    value = precio_editText,
                    onValueChange = { precio_editText = it },
                    label = { Text("Precio") })
                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    { galleryLauncher.launch("image/*") },
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) { Text("Seleccionar foto") }

                Spacer(modifier = Modifier.height(10.dp))

                Button({
                    guardar(
                        nombre_editText, descripcion_editText, precio_editText, imagenUri!!
                    )
                }, modifier = Modifier.padding(horizontal = 12.dp)) { Text("Guardar producto") }


            }
        }

    }
}

@Composable
fun ListaCompra(productos: List<Producto>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(productos) { producto ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    //Imagen
                    AsyncImage(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(horizontal = 10.dp),
                        contentScale = ContentScale.Crop,
                        model = producto.imagen,
                        contentDescription = "Imagen producto"
                    )


                    // Descripción y nombre
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = producto.nombre, style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = producto.descripcion,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 14.sp
                        )
                    }

                    // Precio
                    Text(
                        text = "${producto.precio}€", style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun mostrarScreen() {
    anadirProductoDialog({}, { a, v, b, c -> }, {})
}