package com.example.ejemplo_intents_compose

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplo_intents_compose.ui.theme.Ejemplo_intents_composeTheme


class MainActivity : ComponentActivity() {

    //Realmente es la action implicita ACTION_OPEN_DOCUMENT
    //https://developer.android.com/guide/components/intents-common?hl=es-419#OpenFile

    //En esta zona se explica todo
    //https://developer.android.com/training/data-storage/shared/documents-files?hl=es-419#open-file
    val lanzador_abrirDocumento =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            //Nos retorna la uri del fichero seleccionado
                uri ->
            uri?.let {

                // Tomar permisos persistentes DE LECTURA,
                //Android con la action implicita OPEN_DOCUMENT, ofrece un permiso temporal
                //de lectura, FLAG_GRANT_READ_URI_PERMISSION, si se quiere usar ese archivo
                //posteriormente (p.ej: finaliza la app), ese permiso debe persistir
                //https://developer.android.com/training/data-storage/shared/documents-files?hl=es-419#persist-permissions
                /*    contentResolver.takePersistableUriPermission(
                        it,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION
                    )*/

                Log.i("URI", it.toString())
                Log.i("CONTENIDO FICHERO", leer_fichero(it))
            }
        }

    /*
    FUNCION PARA LEER UN FICHERO DESDE UNA URI
    DEVUELVE UN STRING
     */
    fun leer_fichero(uri: Uri): String {
        //Abrimos el archivo ,la función use lo cierra al finalizar y pasa a la lambda
        //el objeto que devuelve openIputStream, que es un InputStream
        return contentResolver.openInputStream(uri)?.use { input ->
            //bufferedReader es una función de extensión
            //de la clase InputStream, que crea  un objeto InputStreamReader (un buffer),
            //con un charset UTF8
            input.bufferedReader().use {
                //readText es una función de extensión que proporciona Android
                //sobre la clase Reader, que lee todos los caracteres del InputStreamReader
                // y retorna un String
                it.readText()
            }
            //Si no se puede abrir el fichero devuelve null y por lo tanto orEmpty() devuelve
            //la cadena vacia
        }.orEmpty()


        /* Es lo mismo que esto
        val stringBuilder = StringBuilder()
       contentResolver.openInputStream(uri)?.use { inputStream ->
           BufferedReader(InputStreamReader(inputStream)).use { reader ->
           var line: String? = reader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = reader.readLine()
            }//Fin del while
        }
    }
         */


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo_intents_composeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Pantalla(
                        Modifier.padding(innerPadding),
                        lanzador_abrirDocumento
                    )
                }
            }
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

@Composable
fun Pantalla(modificador: Modifier = Modifier, lanzador: ActivityResultLauncher<Array<String>>?) {
    val lanzadorContactos =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickContact()) { uri ->
            uri?.let { Log.i("Contacto", it.toString()) }
        }
    Box(modifier = modificador.padding(16.dp), contentAlignment = Alignment.Center)
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                //Hay que pasarle un listado de tipos mimes para que el
                //selector de ficheros seleccione solo unos ficheros
                //en este caso solo de texto
                lanzador?.launch(arrayOf("text/plain"))

            }) { Text("Abrir Documento") }
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Button(onClick = {

            }) { Text("Seleccionar Contactos") }

            Spacer(modifier = Modifier.padding(top = 8.dp))
            Button(onClick = {
                lanzadorContactos.launch()
            }) { Text("Solicitar Permisos") }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ejemplo_intents_composeTheme {
        Pantalla(lanzador = null)
    }
}