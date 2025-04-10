package cat.itb.m78.exercices.exercicis02_State

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SecretNum() {
    val text = remember { mutableStateOf("") }
    val nums = 0..100
    val randomNum = remember{nums.random()}
    val bucleTimes = remember{ mutableStateOf(0)}

    var numberString by remember { mutableStateOf("") }
    var number by remember { mutableStateOf(0) }

    Column {
        TextField(
            value = numberString,
            label = { Text(text = "Introduiex un nombre de 1 a 100") },
            onValueChange = { numberString = it }
        )

        Button(onClick = {
            number = numberString.toInt()
            if (randomNum == number){
                text.value = "Has encertat!"
            } else{
                if (randomNum > number){
                    text.value = "El número que busques és més gran"
                    bucleTimes.value += 1
                } else{
                    text.value = "El número que busques és més petit"
                    bucleTimes.value += 1
                }
            }
        }){
            Text("IntNum")
        }
        Text(text.value)
        Text("Número d'intents: " + bucleTimes.value)
    }
}