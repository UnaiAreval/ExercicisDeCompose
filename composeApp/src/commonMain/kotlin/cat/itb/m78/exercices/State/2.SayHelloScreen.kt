package cat.itb.m78.exercices.State

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SayHello(){
    var name by remember { mutableStateOf("") }
    var showHelloMsg by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = name,
            label = { Text(text = "") },
            onValueChange = { name = it }
        )

        Button(onClick = {
            showHelloMsg = true
        }){
            Text("SayHello")
        }
        if (showHelloMsg) {
            AlertDialog(
                onDismissRequest = {},
                confirmButton = {},
                text = { Text("Hello " + name) }
            )
        }
    }
}