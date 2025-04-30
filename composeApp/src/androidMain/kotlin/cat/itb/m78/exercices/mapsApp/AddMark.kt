package cat.itb.m78.exercices.mapsApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddMarkToTheMap(
    cords: LatLng,
    backToTheMap: () -> Unit
){
    val newMarkTitle = remember { mutableStateOf("") }
    val newMarkLat = remember { mutableStateOf("") }
    val newMarkLng = remember { mutableStateOf("") }

    Column (horizontalAlignment = Alignment.CenterHorizontally){
        TextField(
            value = newMarkTitle.value,
            label = { Text(text = "") },
            onValueChange = { newMarkTitle.value = it },
        )
        if (newMarkTitle.value == ""){
            Text("Cal un titol pel marcador")
        }
        TextField(
            value = newMarkTitle.value,
            label = { Text(text = "") },
            onValueChange = { newMarkTitle.value = it },
        )
        if (newMarkLat.value.all { it.isDigit() } || newMarkLat.value.indexOf("-") != -1){
            Text("La latitud ha de ser un número", color = Color.Red)
        }
        TextField(
            value = newMarkLng.value,
            label = { Text(text = "") },
            onValueChange = { newMarkLng.value = it },
        )
        if (newMarkLng.value.all { it.isDigit() } || newMarkLng.value.indexOf("-") != -1){
            Text("La longitud ha de ser un número", color = Color.Red)
        }
        //Els if estan del rebes
        Row {
            Button(
                onClick = {
                    backToTheMap()
                }) {
                Text("Cancelar")
            }
            Button(
                onClick = {
                    if (newMarkLng.value.all { it.isDigit() } ||
                        newMarkLng.value.indexOf("-") != -1 ||
                        newMarkLat.value.all { it.isDigit() } ||
                        newMarkLat.value.indexOf("-") != -1)
                    //Guardar el marcador
                    backToTheMap()
                }) {
                Text("Afegir")
            }
        }
    }
}