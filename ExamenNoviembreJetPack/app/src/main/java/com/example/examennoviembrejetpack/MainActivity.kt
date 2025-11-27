package com.example.examennoviembrejetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.examennoviembrejetpack.ui.theme.ExamenNoviembreJetPackTheme
import com.example.examennoviembrejetpack.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenNoviembreJetPackTheme {
                listaCompraInterfaz()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaCompraTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = "LISTA DE LA COMPRA") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Purple40,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun listaCompraInterfaz(modifier: Modifier = Modifier) {
    var fecha: String = ""
    Scaffold(
        modifier.fillMaxSize(),
        topBar = { ListaCompraTopBar() }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(text = "DATOS LISTA DE LA COMPRA", textAlign = TextAlign.End)
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Column {
                    Row {
                        Text(text = "FECHA_COMPRA", textAlign = TextAlign.End)
                        TextField(
                            value = fecha,
                            onValueChange = { fecha = it }
                        )
                    }
                }
                Column {
                    Row {
                        Text(text = "TIPO_PRODUCTO", textAlign = TextAlign.End)
                        DropdownMenu(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            expanded = true,
                            onDismissRequest = {}) { }
                    }
                }
                Column {
                    Row {
                        Text(text = "NOMBRE_PRODUCTO", textAlign = TextAlign.End)
                        TextField(
                            value = fecha,
                            onValueChange = { fecha = it }
                        )
                        Switch(
                            checked = false,
                            onCheckedChange = {},
                            modifier = modifier.fillMaxWidth()
                        )
                    }
                }
                Column {
                    Row {
                        Text(text = "TIPO_PRODUCTO", textAlign = TextAlign.End)
                        DropdownMenu(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            expanded = true,
                            onDismissRequest = {}) { }
                    }
                }
            }
            Button(onClick = {}) { }
        }

    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    listaCompraInterfaz()
}