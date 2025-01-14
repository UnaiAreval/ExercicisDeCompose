package cat.itb.m78.exercices.State

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun GoodTime(){
    val text = remember { mutableStateOf("Good !?") }
    Button(onClick = {
        text.value = "Good Morning"
    }) {
        Text(text.value)
    }
    Button(onClick = {
        text.value = "Good Night"
    }) {
        Text(text.value)
    }
}