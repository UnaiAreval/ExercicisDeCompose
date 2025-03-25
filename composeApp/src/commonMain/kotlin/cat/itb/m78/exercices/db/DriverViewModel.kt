package cat.itb.m78.exercices.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DriverViewModel : ViewModel() {
    val myTableQueries = database.myTableQueries
    val all = myTableQueries.selectAll().executeAsList()

    fun Driver(){
        database.myTableQueries.insert("holiwi")
    }

    init {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                database.myTableQueries.insert("some text")
            }
        }
    }
}
