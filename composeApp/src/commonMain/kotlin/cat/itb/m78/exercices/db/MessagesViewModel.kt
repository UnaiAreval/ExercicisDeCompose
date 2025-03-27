package cat.itb.m78.exercices.db

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MessagesViewModel : ViewModel(){
    val messages: Flow<List<Messages>> = database.messagesQueries.selectAll().asFlow().mapToList(Dispatchers.IO)
    val newMessageText = mutableStateOf("")

    fun insertMessage(){
        viewModelScope.launch(Dispatchers.Default) {
            database.messagesQueries.insert(newMessageText.value)
            newMessageText.value = ""
        }
    }

    fun updateNewMessageText(text: String){
        newMessageText.value = text
    }
}
/*
class MessagesViewModel : ViewModel(){
    val messages = mutableStateOf<List<Messages>>(listOf())
    val newMessageText = mutableStateOf("")

    private fun reloadMessages(){
        viewModelScope.launch(Dispatchers.Default) {
            messages.value = database.messagesQueries.selectAll().executeAsList()
        }
    }

    fun insertMessage(){
        viewModelScope.launch(Dispatchers.Default) {
            database.messagesQueries.insert(newMessageText.value)
            newMessageText.value = ""
            reloadMessages()
        }
    }

    fun updateNewMessageText(text: String){
        newMessageText.value = text
    }

    init {
        reloadMessages()
    }
}
*/