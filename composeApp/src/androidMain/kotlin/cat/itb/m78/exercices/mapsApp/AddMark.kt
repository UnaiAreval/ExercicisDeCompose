package cat.itb.m78.exercices.mapsApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.android.gms.maps.model.LatLng
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.cameraIcon
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddMarkToTheMap(
    cords: LatLng,
    lastImageUri: String,
    backToTheMap: () -> Unit,
    kipImageUri: (String) -> Unit,
    addNewMark: (Mark) -> Unit
){
    val newMarkTitle = remember { mutableStateOf("") }
    val newMarkDescription = remember { mutableStateOf("") }
    val newMarkLat = remember { mutableStateOf("${cords.latitude}") }
    val newMarkLng = remember { mutableStateOf("${cords.longitude}") }
    val imageUri = remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ){
        Text("Titol del grafiti: ", fontSize = 20.sp)
        TextField(
            value = newMarkTitle.value,
            label = { Text(text = "") },
            onValueChange = { newMarkTitle.value = it },
        )
        if (newMarkTitle.value == ""){
            Text("* Cal un titol pel marcador", color = Color.Red)
        }

        Spacer(modifier = Modifier.size(30.dp))

        Text("Cordenades: ", fontSize = 20.sp)
        Text(" · Latitud: ${newMarkLat.value} \n · Longitud ${newMarkLng.value}")

        Spacer(modifier = Modifier.size(30.dp))

        Column {
            Button (
                modifier = Modifier.padding(10.dp),
                onClick = {
                    //fer la foto
                    kipImageUri(imageUri.value)
                }
            ){
                if (imageUri.value.isNotEmpty()){
                    AsyncImage(
                        model = imageUri.value,
                        contentDescription = "mark image",
                        modifier = Modifier.size(400.dp)
                    )
                }
                else {
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            painter = painterResource(Res.drawable.cameraIcon),
                            modifier = Modifier.size(150.dp).padding(15.dp).clip(CircleShape),
                            contentDescription = null
                        )
                        Text("Afegir foto")
                    }
                }
            }

            if (lastImageUri.isNotEmpty()){
                Button(onClick = {
                    imageUri.value = lastImageUri
                }) {
                    AsyncImage(
                        model = lastImageUri,
                        contentDescription = "last photo uri",
                        modifier = Modifier.size(400.dp)
                    )

                    Text("Afegir ultima foto")
                }
            }
        }
        if (imageUri.value.isEmpty()){ Text("* Cal una imatge del grafiti", color = Color.Red) }

        Spacer(modifier = Modifier.size(30.dp))

        Text("Descripció: ", fontSize = 15.sp)
        TextField(
            value = newMarkDescription.value,
            label = { Text(text = "") },
            onValueChange = { newMarkDescription.value = it },
        )

        Spacer(modifier = Modifier.size(40.dp))

        Row (verticalAlignment = Alignment.Bottom){
            Button(
                onClick = {
                    backToTheMap()
                }) {
                Text("Cancelar")
            }
            Button(
                onClick = {
                    if (newMarkTitle.value.isNotEmpty() && imageUri.value.isNotEmpty()){
                        addNewMark(Mark(newMarkLat.value, newMarkLng.value, newMarkTitle.value, imageUri.value, ""))
                    }
                    backToTheMap()
                }) {
                Text("Afegir")
            }
        }
        if (newMarkTitle.value.isEmpty() && imageUri.value.isEmpty()) {
            Text("* Si no hi ha un titol i una imatge no es guardara el marcador", color = Color.Red)
        }
        else {
            if (newMarkTitle.value.isEmpty()){
                Text("* Cal un titol per guardar el marcador", color = Color.Red)
            } else if (imageUri.value.isEmpty()){
                Text("* Cal una imatge per guardar el marcador", color = Color.Red)
            }
        }
    }
}