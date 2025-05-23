package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.painterResource

@Composable
fun TrivialMenu(
    navigateToSettings:() -> Unit,
    navigateToQuestions: (Int)-> Unit
){
    Column (modifier = Modifier.fillMaxSize().background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(
            painter = painterResource(Res.drawable.trivial),
            modifier = Modifier.size(150.dp).padding(15.dp).clip(CircleShape),
            contentDescription = null
        )
        Button(onClick = { navigateToQuestions(rounds) })
        { Text("Start Game") }
        Button(onClick = {
                navigateToSettings()
        }){
            Text("Settings")
        }

        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Number of rounds: $rounds", modifier = Modifier.padding(10.dp), textAlign = TextAlign.Center)
        }
    }
}