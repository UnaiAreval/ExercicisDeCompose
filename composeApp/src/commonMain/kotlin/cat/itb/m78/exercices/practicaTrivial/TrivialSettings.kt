package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(){
    var newRounds: Int

    Column {
        Row {

            Column {
                Text("Select rounds amount: ")
                Button(onClick = { newRounds = 5 }){ Text("5 rounds") }
                Button(onClick = { newRounds = 10}){ Text("10 rounds") }
                Button(onClick = { newRounds = 15}){ Text("15 rounds") }
            }
        }
        Button(onClick = {  }){
            Text("Return to the game menu")
        }
    }
}
