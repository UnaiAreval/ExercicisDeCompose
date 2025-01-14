package cat.itb.m78.exercices.State

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
fun secretNum() {
    val numbers = 0..100
    val randomNum = numbers.random()

    var num by remember { mutableStateOf(0) }

    Column {
        /*TextField(
            value = num,
            label = { Text(text = "") },
            onValueChange = { num = it }
        )*/

        Button(onClick = {

        }){
            Text("IntNum")
        }
    }
}