package cat.itb.m78.exercices.practicaAPI_DB

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import cat.itb.m78.exercices.db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GamesViewModel : ViewModel() {
    val favoriteGames: Flow<List<Long>> = database.favoriteGamesQueries.selectAll().asFlow().mapToList(Dispatchers.IO)
    val gameId = mutableStateOf(0)

    fun insertFavGame(){
        viewModelScope.launch {
            database.favoriteGamesQueries.insert()
        }
    }

    fun deleteFavGame(){

    }
}