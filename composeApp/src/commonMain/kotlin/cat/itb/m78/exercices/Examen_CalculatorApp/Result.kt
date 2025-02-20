package cat.itb.m78.exercices.Examen_CalculatorApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ResultScreen(
    result: Int,
    navigateBackToCalculator: () -> Unit
){
    Column (
        modifier = Modifier.background(Color.Yellow).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("The final result is \n${result}", textAlign = TextAlign.Center)
        Button(onClick = {
            navigateBackToCalculator()
        }){
            Text("Go back")
        }
    }
}