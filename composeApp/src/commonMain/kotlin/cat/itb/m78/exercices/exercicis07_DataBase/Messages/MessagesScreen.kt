package cat.itb.m78.exercices.exercicis07_DataBase.Messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.db.Messages
import cat.itb.m78.exercices.db.MessagesViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Composable
fun MessagesScreen(){
    val viewModel = viewModel { MessagesViewModel() }

    val messages = viewModel.messages.collectAsStateWithLifecycle(listOf()).value
    MessagesScreen(messages, viewModel.newMessageText.value, viewModel::insertMessage, viewModel::updateNewMessageText)


}

@Composable
fun MessagesScreen(messages: List<Messages>,
                   newMessageText: String,
                   onInsert: () -> Unit,
                   onTextUpdate: (String) -> Unit){

    Column {
        Row {
            OutlinedTextField(newMessageText, onTextUpdate)
            Button(onClick = onInsert){
                Text("Add")
            }
        }
        LazyColumn {
            for (message in messages)
            item{
                Text(message.text)
            }
        }
    }
}