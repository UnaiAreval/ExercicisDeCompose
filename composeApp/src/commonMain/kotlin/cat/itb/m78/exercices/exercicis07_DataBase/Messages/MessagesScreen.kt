package cat.itb.m78.exercices.exercicis07_DataBase.Messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

object MessagesNavigation {
    @Serializable
    data class DisplayMessages(val messages: List<String>)
}

@Composable
fun MessagesScreen(){
    /*val viewModel = viewModel {  }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MessagesNavigation.DisplayMessages){
        composable<MessagesNavigation.DisplayMessages> {
            DisplayAndAddMessages()
        }
    }

     */
}

@Composable
fun DisplayAndAddMessages(messages: List<String>){
    val newMessage = remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            LazyColumn {
                for (i in 0..messages.size / 2) {
                    item {
                        Text(messages[i])
                    }
                }
            }

            LazyColumn {
                for (i in messages.size / 2 + 1..<messages.size) {
                    item {
                        Text(messages[i])
                    }
                }
            }
        }

        TextField(
            value = newMessage.value,
            label = { Text(text = "") },
            onValueChange = { newMessage.value = it }
        )
        Button(onClick = {

        }){
            Text("Afegir missatge")
        }
    }
}