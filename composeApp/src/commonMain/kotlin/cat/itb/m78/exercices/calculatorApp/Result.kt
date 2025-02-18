package cat.itb.m78.exercices.calculatorApp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.painterResource

@Composable
fun ResultScreen(result: Int){
    Column {
        Text("The final result is")
        Text("$result")
        Image(
            painter = painterResource(Res.drawable.trivial),
            modifier = Modifier.size(150.dp).padding(15.dp),
            contentDescription = null
        )
    }
}