package com.example.ui_imperativavsdeclarativa

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui_imperativavsdeclarativa.ui.theme.UI_ImperativavsDeclarativaTheme

class MainActivity : ComponentActivity() {
    /*companion object {
        var count = mutableStateOf(0)
    }

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UI_ImperativavsDeclarativaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Greeting(

                        Modifier.padding(innerPadding)
                    )


                }
            }
        }
    }
}


@Composable
fun Greeting(modificador: Modifier = Modifier) {

    //Variable que se recuerda "remember" su valor en las diferentes invocaciones de Greeting
    //Greeting se ejecuta cada vez que haya una modificación en la UI (reactivo)
    //mutableStateOf define una variable observable a cambios, se la llama variable reactiva
    //Va a implicar que va a ejecutar el código donde esta definida Greeting cuando se produzca
    //un cambio en su valor
    //rememberSaveable guarda el valor usando por "debajo" SaveInstanceState ante destrucciones
    //de la activity
    //by va a permitir acceder al valor de count directamente, delegación de propiedades
    //sin by declaramos count:rememberSaveable{....} y para acceder al valor tenemos
    //que usar la propiedad value.

    //de la pantalla donde se encuentre la variabla
    //var count=0; //De esta manera, el valor se actualiza pero la interfaz no se repinta, y por lo tanto no se ve el valor correcto
    //Si se declara la variable count usando companion object, para poder acceder a ella e incrementarla hay que usar $MainActivity.count.vale
    //Si declaro aqui la variable var count=mustableStateOf(0), la interfaz se repinta pero cada vez que pulsamos se vuelve a cargar la función y su valor de restablece a 0
    var count by rememberSaveable{ mutableStateOf(0)} //De esta manera se consigue que cuando se cambie el valor de esta variable, se repinta la sección
    Box(
        modifier = modificador
            .fillMaxSize()
            .background(Color.Cyan),
        contentAlignment = Alignment.Center
    ) {

        Column {
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                text = "Contador: $count"
            )

            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = { count++}) {
                //Recomposición inteligente, esto no se recompone porque no depende de count
                Text(fontSize = 20.sp, text = "Sumar")
                Log.i("INFO", "SE REPINTA COLUM")
                //El contenedor del button es un Row, por eso si añadimos otro componente aqui, lo pone uno al lado del otro
            }
        }


    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UI_ImperativavsDeclarativaTheme {
        Greeting()
    }
}