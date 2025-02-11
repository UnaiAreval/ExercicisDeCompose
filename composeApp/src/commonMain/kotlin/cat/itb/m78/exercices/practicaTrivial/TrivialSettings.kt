package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    navigateBackToMenu:(Int)-> Unit
){
    val newRounds = remember{ mutableStateOf(5) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Row {
            Column {
                Text("Select rounds amount: ", textAlign = TextAlign.Center)
                Button(onClick = { newRounds.value = 5 }){ Text("5 rounds") }
                Button(onClick = { newRounds.value = 10}){ Text("10 rounds") }
                Button(onClick = { newRounds.value = 15}){ Text("15 rounds") }
                Text("Current amount: ${newRounds.value}", textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.size(250.dp))
        Button(onClick = { navigateBackToMenu(newRounds.value) }){
            Text("Return to the game menu")
        }
    }
}
