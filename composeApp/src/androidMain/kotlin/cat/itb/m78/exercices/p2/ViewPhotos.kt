package cat.itb.m78.exercices.p2

import android.content.Context
import android.provider.MediaStore
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ViewPhotos(navigateToCamera: ()-> Unit){
    Column (horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = { navigateToCamera() }) { Text("Go to camera") }
        LazyColumn {
            /*
            for (image in ){
                item {
                    AsyncImage(
                        model = image,
                        contentDescription = "galery image",
                        modifier = Modifier.size(400.dp, 200.dp)
                    )
                }
            }
            */
        }
    }
}