package com.example.examen2parcial25_26.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen2parcial25_26.R
import com.example.examen2parcial25_26.ui.theme.BlueOnPrimary
import com.example.examen2parcial25_26.ui.theme.BluePrimary
import com.example.examen2parcial25_26.ui.theme.RedError
import com.example.examen2parcial25_26.ui.theme.ToolbarTitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun miTopAppBar(guardarContactos: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 16.dp)
            )
        },
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                contentAlignment = Alignment.CenterStart
            ) { Text("GEST-CONTACT", style = ToolbarTitleStyle, fontSize = 20.sp) }
        },
        actions = {
            IconButton(guardarContactos, colors = IconButtonDefaults.iconButtonColors(contentColor = BlueOnPrimary)) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Guardar contactos"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BluePrimary,
            titleContentColor = BlueOnPrimary
        )
    )
}