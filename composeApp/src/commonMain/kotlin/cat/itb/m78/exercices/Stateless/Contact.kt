package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import org.jetbrains.compose.resources.painterResource

@Composable
fun Contact(){
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            modifier = Modifier.size(150.dp).padding(20.dp),
            contentDescription = null
        )
        Text("Marta Casserres")
    }
}