package cat.itb.m78.exercices.p2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun DisplayPhoto(
    photoUrl: String,
    navigateToCamera: () -> Unit
){
    Box {
        AsyncImage(
            model = photoUrl,
            contentDescription = "new photo",
            modifier = Modifier.fillMaxSize()
        )
        Button(
            onClick = { navigateToCamera() },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) { Text("Go to camera") }
    }
}