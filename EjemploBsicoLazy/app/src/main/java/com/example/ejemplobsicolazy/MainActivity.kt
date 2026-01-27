package com.example.ejemplobsicolazy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ejemplobsicolazy.ui.theme.EjemploBásicoLazyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploBásicoLazyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    pantalla(modificador = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun pantalla(modificador: Modifier = Modifier) {
    Column(
        modifier = modificador.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Lista de elementos")
        Spacer(modifier = Modifier.padding(8.dp))
        MyLazyColumn()
    }

}

data class Elemento(val texto: String, var estado_check: Boolean) {}

@Composable
fun MyLazyColumn() {
    var context = LocalContext.current
    var lista by remember { mutableStateOf(List(100) { Elemento("Elemento $it", false) }) }
    LazyColumn {
        itemsIndexed(lista, key = { index, elemento -> elemento.hashCode() }) { indice, elemento ->
            var chequeado by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text("Indice: $indice - ${elemento.texto}")
                Spacer(modifier = Modifier.weight(1f))
                Checkbox(
                    elemento.estado_check,
                    onCheckedChange = {
                        //Aqui tendremos que provocar un cambio en la lista para
                        //que se recomponga la lista
                        //Creo una nueva lista con todos los elementos igual que la lista original excepto
                        //el valor del atributo chequeado del elemento clicado
                        lista = lista.map { item ->
                            if (elemento == item) {
                                item.copy(estado_check = !chequeado)
                            } else {
                                item
                            }
                        }
                        elemento.estado_check = !elemento.estado_check
                    })
                Button(onClick = {
                    Toast.makeText(context, "Has pulsado $elemento", Toast.LENGTH_SHORT).show()
                    lista = lista.toMutableList().apply { remove(elemento) }

                }) {
                    Text(text = "Aceptar")
                }
            }
        }
    }
}



