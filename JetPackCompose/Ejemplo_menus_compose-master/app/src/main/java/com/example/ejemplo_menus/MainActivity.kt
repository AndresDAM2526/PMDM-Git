package com.example.ejemplo_menus

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.ejemplo_menus.ui.theme.Ejemplo_menusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo_menusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Pantalla_Principal(modificador = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Pantalla_Principal(modificador: Modifier= Modifier)
{
    var valor_texto by remember { mutableStateOf("") }
    var menuboton_expandido by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(modifier=modificador.fillMaxSize(), contentAlignment = Alignment.Center)
    {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            //Meto en el mismo contexto el boton y el menu, porque sino el menu se fija sobre
            //el layout padre
            Box( )
            {
                Column {
                Button(onClick = {
                    menuboton_expandido = !menuboton_expandido
                }) { Text("Abrir menú") }
                Menu_boton(
                    menuboton_expandido,
                    //State hosting
                    { menuboton_expandido = !menuboton_expandido },
                    //State hosting, trasladar hacia arriba la responsabilidad de gestionar la funcionalidad
                    onclickItem = {
                        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        menuboton_expandido = false
                    })
            }}
            Spacer(modifier = Modifier.padding(10.dp))
            TextField(value = valor_texto, onValueChange ={valor_texto = it}  )
        }
    }
}

@Composable
fun Menu_boton(menu_expandido: Boolean,dissmis: () -> Unit = {},onclickItem: (String) -> Unit = {} ) {




    DropdownMenu(expanded=menu_expandido, onDismissRequest = dissmis , offset = DpOffset(x=80.dp,y=0.dp)) {
        DropdownMenuItem(text = { Text("Opción 1") }, leadingIcon = { Icon(painter = painterResource(id = android.R.drawable.ic_menu_call), contentDescription = null) }, onClick = { onclickItem("Opcion 1") })
        DropdownMenuItem(text = { Text("Opción 2") }, onClick = { onclickItem("Opcion 2") })
        DropdownMenuItem(text = { Text("Opción 3") }, onClick = { onclickItem("Opcion 3") })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejemplo_menusTheme {
       Pantalla_Principal()
    }
}