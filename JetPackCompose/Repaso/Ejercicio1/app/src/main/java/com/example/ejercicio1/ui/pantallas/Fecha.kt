package com.example.ejercicio1.ui.pantallas

import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiDatePicker(
    modifier: Modifier,
    bitmap: Bitmap?,
    pickerState: DatePickerState,
    selectedDateMillis: Long?,
    onDateChange: (Long) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val selectedDate = selectedDateMillis?.let { convertMillisToDate(it) } ?: ""
    Column {
        OutlinedTextField(
            value = selectedDate,
            modifier = Modifier,
            onValueChange = { },
            label = { Text(text = "Selecciona", fontSize = 11.sp) },
            readOnly = true,
            trailingIcon = {
                //Cuando hacemos click en el icono aparece, pero
                //no interactua si esta visible el Popup
                IconButton(onClick = {
                    showDatePicker = !showDatePicker
                    Log.i("INFO", "Click en el icono fecha")
                }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        //painter= painterResource(R.drawable.nombre)  A traves del ResourceManager se crea y se añade de esta manera
                        contentDescription = "Selecciona fecha"
                    )
                }
            }

        )
        LaunchedEffect(pickerState.selectedDateMillis) {
            pickerState.selectedDateMillis?.let {
                onDateChange(it)
                showDatePicker = false
            }
        }
        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        //Desplazo el DatePicker 64dp con respecto a la parte superior
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    DatePicker(
                        //De esta forma no hay que estar pendiente
                        //de cambios de valores, es Material3
                        state = pickerState,
                        //Permite introducir o no las fechas de forma manual
                        showModeToggle = false,

                        )
                }
            }
        }
        bitmap?.let { Image(bitmap = it.asImageBitmap(), contentDescription = null,modifier.size(250.dp)) }

    }

}

//Funcion para convertir milisegundos a una fecha con formato dia/mes/año
fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}