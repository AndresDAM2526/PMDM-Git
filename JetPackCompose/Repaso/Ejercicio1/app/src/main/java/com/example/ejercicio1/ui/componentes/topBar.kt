package com.example.ejercicio1.ui.componentes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topbar(modifier: Modifier = Modifier) {
    TopAppBar(title = { Text("Likes") }, navigationIcon = {
        Icon(
            modifier = modifier.padding(horizontal = 10.dp),
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = "Likes"
        )
    }, actions = {

        Icon(
            imageVector = Icons.Outlined.AccountBox, contentDescription = "Likes"
        )
        Spacer(modifier = modifier.width(10.dp))
        Icon(
            imageVector = Icons.Outlined.AccountBox, contentDescription = "Likes"
        )
        Spacer(modifier = modifier.width(10.dp))
        Icon(
            imageVector = Icons.Outlined.AccountBox, contentDescription = "Likes"
        )

    })
}