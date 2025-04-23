package cat.itb.m78.exercices.p2

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.heartIcon
import org.jetbrains.compose.resources.painterResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Camera(
    navigateToPhotosList: ()-> Unit,
    takePhoto: (Context)-> Unit,
    surfaceRequest: SurfaceRequest?,
    context: Context
){
    Scaffold (bottomBar = {
        BottomAppBar (
            actions = {
                NavigationBarItem(
                    onClick = { takePhoto(context) },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.heartIcon),
                            contentDescription = "camera icon",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = { Text("Take Photo") }
                )
                //Button(onClick = { takePhoto(context) }) { Text("Take Photo") }
                Button(onClick = { navigateToPhotosList() }) { Text("Galeri") }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }) {
        surfaceRequest?.let { request ->
            CameraXViewfinder(
                surfaceRequest = request,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}