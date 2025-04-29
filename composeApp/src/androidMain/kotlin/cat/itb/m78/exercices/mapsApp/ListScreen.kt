package cat.itb.m78.exercices.mapsApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MarkerListScreen(
    marks: List<Mark>
) {
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Text("Llistat de marcadors", fontSize = 30.sp)
        Spacer(modifier = Modifier.size(30.dp))
        LazyColumn {
            for (mark in marks){
                item {
                    Text(mark.title, fontSize = 15.sp)
                    Text("Coordenades: \n ·Latitud: ${mark.latitude} \n ·Longitud: ${mark.longitude}")
                    Spacer(modifier = Modifier.size(50.dp))
                }
            }
        }
    }
}