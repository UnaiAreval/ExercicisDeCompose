package cat.itb.m78.exercices.exercicis01_Stateless

import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import org.jetbrains.compose.resources.painterResource

@Composable
fun Resource(){
    Column {
        Text("This is a resource string")
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            modifier = Modifier.size(150.dp).padding(20.dp),
            contentDescription = null,
        )
    }
}