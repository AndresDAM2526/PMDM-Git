package com.example.ejemplo_menus


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplo_menus.ui.theme.Ejemplo_menusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo_menusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MiExposedDropDownMenuBox(modificador = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiExposedDropDownMenuBox(modificador: Modifier = Modifier) {
    var menu_expandido by remember { mutableStateOf(false) }
    var valor_seleccionado by remember { mutableStateOf("") }
    var idiomas = listOf("Español", "Inglés", "Francés")
    //El state está continuamente vigilando si hay cambios, se vuelve a construir la interfaz.Se mete de un remember para que los valores no se pierdan con la reconstrucción de la interfaz
    ExposedDropdownMenuBox(
        expanded = menu_expandido,
        onExpandedChange = { menu_expandido = !menu_expandido },
        modifier = modificador.padding(top = 32.dp)
    ) {
        //Como contenido tiene un TextField no editable
        Log.i("INFO", "Se ejecuta MiExposedDropDownMenu")
        TextField(
            value = valor_seleccionado,
            readOnly = true,//No voy a permitir introducir nada
            onValueChange = {},
            label = { Text("Idioma") },
            //Esto es necesario para que se ancle el menu al TextField, si no se pone no aparece el menu
            //El primer parametro de menuAnchor es el tipo de menu, existen 3 tipos
            modifier = Modifier.menuAnchor(
                type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                enabled = true
            ),
            //Icono que se muestra al final del TextField, es un composable
            trailingIcon = {
                //Función composable que muestra un icono diferente (flecha) en función del valor
                // que se le pasa, presenta una flecha hacia abajo o hacia arriba
                ExposedDropdownMenuDefaults.TrailingIcon(menu_expandido)
            }
        )

        DropdownMenu(
            expanded = menu_expandido,
            onDismissRequest = {
                menu_expandido = false
            }, //Cuando se cierra el menu lo que quiero hacer
        ) {
            idiomas.forEach {
                DropdownMenuItem(
                    text = { Text("$it") },
                    onClick = {
                        valor_seleccionado = it
                        menu_expandido = false
                    })
            }
        }
        /*
        {
            DropdownMenuItem(text = { Text("Ingles") }, onClick = {
                valor_seleccionado = "Ingles"
                menu_expandido = false
            })
            DropdownMenuItem(text = { Text("Español") }, onClick = {
                valor_seleccionado = "Español"
                menu_expandido = false
            })
            DropdownMenuItem(text = { Text("Frances") }, onClick = {
                valor_seleccionado = "Frances"
                menu_expandido = false
            })
            */

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejemplo_menusTheme {
        MiExposedDropDownMenuBox()
    }
}