package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ResultScreen(
    navigateToTrivialMenu:()-> Unit,
    points: Int
){

    val congratsMesage: String
    if ((points < rounds / 2 + 1 && rounds % 2 == 1) || (points < rounds / 2 && rounds % 2 == 0)) { congratsMesage = "Better luck on the next try" }
    else if (points < rounds / 4 * 3) { congratsMesage = "Not bad" }
    else if (points < rounds) { congratsMesage = "Wel done" }
    else { congratsMesage = "Congratulations!!" }

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text("You got $points points out of $rounds.", textAlign = TextAlign.Center)
        Text(congratsMesage)
        Button(onClick = { navigateToTrivialMenu() }){
            Text("Go back to de start menu")
        }
    }
}