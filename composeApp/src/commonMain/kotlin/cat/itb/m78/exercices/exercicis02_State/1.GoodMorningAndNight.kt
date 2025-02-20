package cat.itb.m78.exercices.exercicis02_State

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun GoodTime(){
    val text = remember { mutableStateOf("?") }

    Column {
        Text("Good " + text.value + "!")

        Button(onClick = {
            text.value = "Morning"
        }) {
            Text("Morning")
        }

        Button(onClick = {
            text.value = "Night"
        }) {
            Text("Night")
        }
    }
}