package com.example.ejemplopermisosmultiples

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ejemplopermisosmultiples.ui.theme.EjemploPermisosMultiplesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploPermisosMultiplesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val permisos =
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.CALL_PHONE
        )
    val context = LocalContext.current
    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permisos ->
            permisos.forEach {
                Toast.makeText(context,if (it.value)"Permiso ${it.key} concedido" else "Permiso ${it.key} denegado",
                    Toast.LENGTH_SHORT).show()
            }
        }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Button(onClick = { permissionLauncher.launch(permisos) }) { Text("Pedir permiso") } }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploPermisosMultiplesTheme {
        Greeting("Android")
    }
}