package cat.itb.m78.exercices.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
/*
class MessagesViewModel : ViewModel(){
    val messagesQueries = database.messagesQueries
    val all = messagesQueries.selectAll().executeAsList()

    fun Message(){
        database.messagesQueries.insert("Holi, que tal?")
    }

    init {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                database.messagesQueries.insert("some message")
            }
        }
    }
}

 */