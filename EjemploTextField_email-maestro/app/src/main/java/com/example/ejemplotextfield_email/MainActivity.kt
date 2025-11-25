package com.example.ejemplotextfield_email

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplotextfield_email.ui.theme.EjemploTextField_emailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploTextField_emailTheme {
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
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Introduce un valor:")
            Spacer(modifier = Modifier.padding(8.dp))
            TextFieldConError()

        }
    }
}

@Composable
fun TextFieldConError() {
    //Variable que va a almacenar el valor introducido en el campo de texto
    var email by remember { mutableStateOf("") }
    var lista by remember { mutableStateOf(mutableListOf<String>()) }
    //Variable para controlar si el valor es valido
    val esValido = email.contains("@")

    Column {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = !esValido
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                lista.add(email)
                Log.i("INFO", "Se ha añadido ")
            }
        ) {
            Text(
                text = "Añadir email"
            )
        }

        Text(
            text = lista.toString()
        )
        //IMPORTANTE RECORDAR QUE ESTA FUNCION SE EJECUTA CUANDO
        //SE MODIFIQUE EL VALOR DEL TEXTO
        if (!esValido) {
            Text(
                text = "Email no válido",
                color = Color.Red,

                )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EjemploTextField_emailTheme {
        Greeting("Android")
    }
}