package cat.itb.m78.exercices.Examen_CalculatorApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Calculator(
    navigateToResultScreen: (Int) -> Unit
){
    val result = remember { mutableStateOf(0) }
    val enterNum = remember { mutableStateOf("") }
    val operation = remember { mutableStateOf("+") }
    val theNumIsNotANum = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.background(Color.Yellow).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text("Result: \n${result.value}", textAlign = TextAlign.Center)
        Row {
            if (operation.value == "+"){
                Button(onClick = {
                    operation.value = "+"
                },
                    modifier = Modifier.size(50.dp, 50.dp).background(Color.Black).padding(5.dp)
                ){
                    Text("+")
                }
            } else {
                Button(onClick = {
                    operation.value = "+"
                }
                ){
                    Text("+")
                }
            }
            if (operation.value == "-"){
                Button(onClick = {
                    operation.value = "-"
                },
                    modifier = Modifier.size(50.dp, 50.dp).background(Color.Black).padding(5.dp)
                ){
                    Text("-")
                }
            }
            else {
                Button(onClick = {
                    operation.value = "-"
                }
                ){
                    Text("-")
                }
            }
            if (operation.value == "*"){
                Button(onClick = {
                    operation.value = "*"
                },
                    modifier = Modifier.size(50.dp, 50.dp).background(Color.Black).padding(5.dp)
                ){
                    Text("*")
                }
            }
            else {
                Button(onClick = {
                    operation.value = "*"
                }
                ){
                    Text("*")
                }
            }
            if (operation.value == "/"){
                Button(onClick = {
                    operation.value = "/"
                },
                    modifier = Modifier.size(50.dp, 50.dp).background(Color.Black).padding(5.dp)
                ){
                    Text("/")
                }
            }
            else {
                Button(onClick = {
                    operation.value = "/"
                }
                ){
                    Text("/")
                }
            }
        }
        TextField(
            value = enterNum.value,
            label = { Text(text = "") },
            onValueChange = { enterNum.value = it }
        )
        Row {
            Button(onClick = {
                navigateToResultScreen(result.value)
            }){
                Text("End")
            }
            Button(onClick = {
                if (enterNum.value.all { it.isDigit() } || enterNum.value.indexOf("-") != -1) theNumIsNotANum.value = false
                else theNumIsNotANum.value = true

                if (!theNumIsNotANum.value) {
                    if (operation.value == "+") result.value += enterNum.value.toInt()
                    else if (operation.value == "-") result.value -= enterNum.value.toInt()
                    else if (operation.value == "*") result.value *= enterNum.value.toInt()
                    else result.value /= enterNum.value.toInt()
                }
                enterNum.value = ""
            }){
                Text("Calculate")
            }
        }
        if (theNumIsNotANum.value){
            Text("You can't introduce something that is not a number", textAlign = TextAlign.Center)
        }
    }
}