package com.example.ejemplolazycolum

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ejemplolazycolum.model.Producto
import com.example.ejemplolazycolum.model.Producto2
import com.example.ejemplolazycolum.ui.theme.EjemploLazyColumTheme
import com.example.ejemplolazycolum.ui.theme.TopBarColor

var listaEjemplo = mutableListOf<Producto>(
    Producto(1, "Manzanas", "1kg de manzanas rojas", 2.5, R.drawable.manzana),
    Producto(2, "Leche", "Leche entera 1L", 1.2, R.drawable.leche),
    Producto(3, "Pan", "Pan integral", 1.0, R.drawable.pan)
)


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploLazyColumTheme {
                var lista2 = remember {
                    mutableStateListOf<Producto2>(

                    )
                }
                var mostrarDialog by remember { mutableStateOf(false) }
                ListaCompraScreen(lista2, { mostrarDialog = true })
                if (mostrarDialog) {
                    anadirProductoDialog(
                        { mostrarDialog = false },
                        { nombreProducto, descripcionProducto, precioProducto ->

                            var producto = Producto2(
                                1,
                                nombreProducto,
                                descripcionProducto,
                                precioProducto.toDouble(),
                            )
                            lista2.add(producto)
                            mostrarDialog=false
                        },
                        {})
                }

            }
        }
    }
}


@Composable
fun ListaCompraScreen(productos: List<Producto2>, onAgregarClick: () -> Unit) {
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
    guardar: (String, String, String) -> Unit,
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
        Column {
            OutlinedTextField(
                value = nombre_editText,
                onValueChange = { nombre_editText = it },
                label = { Text("Nombre") })
            OutlinedTextField(
                value = descripcion_editText,
                onValueChange = { descripcion_editText = it },
                label = { Text("Descripción") })
            OutlinedTextField(
                value = precio_editText,
                onValueChange = { precio_editText = it },
                label = { Text("Precio") })
            Spacer(modifier = Modifier.height(10.dp))

            Button({ galleryLauncher.launch("image/*") }) { Text("Seleccionar foto") }

            Spacer(modifier = Modifier.height(10.dp))

            Button({
                guardar(
                    nombre_editText, descripcion_editText, precio_editText
                )
            }) { Text("Guardar producto") }


        }
    }
}

@Composable
fun ListaCompra(productos: List<Producto2>, modifier: Modifier = Modifier) {
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
                    // Imagen
                    /*
                    Image(
                        painter = painterResource(producto.imagenRes),
                        contentDescription = producto.nombre,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(end = 8.dp),
                        contentScale = ContentScale.Crop
                    )

                     */


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
    anadirProductoDialog({}, { a, v, b, -> }, {})
}