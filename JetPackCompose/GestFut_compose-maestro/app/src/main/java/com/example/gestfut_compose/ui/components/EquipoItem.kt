package com.example.gestfut_compose.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestfut.data.Equipo
import com.example.gestfut.data.EquipoProveedor
import com.example.gestfut_compose.ui.theme.IconosBarra
import com.example.gestfut_compose.R
import com.example.gestfut_compose.funciones.obtenerIdEscudo

@Composable
fun equipoItem(equipo: Equipo,modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(IconosBarra)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column (modifier = Modifier.weight(1f)){
                Image(

                    painterResource(obtenerIdEscudo(equipo.nombre)),
                    contentDescription = "Logo Equipo",
                    modifier = Modifier.size(75.dp),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(equipo.nombre)
            }
            Column(modifier = Modifier.padding(vertical = 10.dp)) {
                Row {
                    Spacer(modifier = Modifier.width(50.dp))
                    Text("PJ")
                    Spacer(modifier = Modifier.width(25.dp))
                    Text("PG")
                    Spacer(modifier = Modifier.width(25.dp))
                    Text("PE")
                    Spacer(modifier = Modifier.width(25.dp))
                    Text("PP")

                }
                Row {
                    val pj = equipo.pg + equipo.pp + equipo.PE
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(text = pj.toString())
                    Spacer(modifier = Modifier.width(35.dp))
                    Text(text = equipo.pg.toString())
                    Spacer(modifier = Modifier.width(35.dp))
                    Text(text = equipo.PE.toString())
                    Spacer(modifier = Modifier.width(35.dp))
                    Text(text = equipo.pp.toString())
                }
            }


        }
    }
}