package cat.itb.m78.exercices.mapsApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.LatLng
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.cameraIcon
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddMarkToTheMap(
    cords: LatLng,
    backToTheMap: () -> Unit
    //navigateToCamera: () -> Unit, comeBackFromCamera: (val uri: String) -> Unit
){
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    val newMarkTitle = remember { mutableStateOf("") }
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

        Button (
            modifier = Modifier.padding(10.dp),
            onClick = {
                if (cameraPermissionState.status.isGranted){
                    //et porta a la camera
                }
                else{
                    //et porta a la pantalla de permissos
                }
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
        if (imageUri.value.isEmpty()){ Text("* Cal una imatge del grafiti", color = Color.Red) }

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
                        //Guardar el marcador
                    }
                    backToTheMap()
                }) {
                Text("Afegir")
            }
        }
        if (newMarkTitle.value.isEmpty() && imageUri.value.isEmpty()) {
            Text("* Si no hi ha un titol i una imatge no es guardara el marcador", color = Color.Red)
        }
    }
}