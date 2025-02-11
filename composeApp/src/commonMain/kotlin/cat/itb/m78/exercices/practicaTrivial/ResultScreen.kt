package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ResultScreen(
    navigateToTrivialMenu:()-> Unit,
    points: Int,
    rounds: Int
){
    var congratsMesage : String = ""
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text("You got $points with $rounds questions answered. $congratsMesage")
        Text(congratsMesage)
        Button(onClick = { navigateToTrivialMenu() }){
            Text("Go back to de start menu")
        }
    }
}