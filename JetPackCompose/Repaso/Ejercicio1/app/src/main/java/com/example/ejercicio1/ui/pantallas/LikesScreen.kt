package com.example.ejercicio1.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun likesScreen(
    likes: Int,
    name: String,
    modifier: Modifier = Modifier,
    aumentar: () -> Unit,
    reducir: () -> Unit
) {

    Box(
        modifier
            .fillMaxSize()
            .background(color = Color.Yellow), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Publicación de Android")
            Spacer(modifier = modifier.height(10.dp))
            Text(text = "Likes $likes")
            Spacer(modifier = modifier.height(10.dp))
            Row {
                Button(onClick = aumentar) {
                    Text(text = "Dar like")
                }
                Spacer(modifier.width(10.dp))
                Button(onClick = reducir) {
                    Text(text = "Reducir")
                }
            }


        }
    }
}