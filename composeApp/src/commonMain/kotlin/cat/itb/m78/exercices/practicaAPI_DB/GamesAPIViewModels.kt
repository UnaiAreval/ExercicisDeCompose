package cat.itb.m78.exercices.practicaAPI_DB

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import cat.itb.m78.exercices.db.database
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class GamesViewModel : ViewModel() {
    val games = mutableStateOf<List<Game>?>(null)
    val filteredGames = mutableStateOf<List<Game>?>(null)
    val favGamesId = mutableStateOf<List<Int>>(emptyList())

    val filterString = mutableStateOf("")

    init {
        viewModelScope.launch {
            games.value = GamesApi.list()
            filteredGames.value = games.value
        }
    }

    fun onUpdateFilter(newFilter: String){
        filterString.value = newFilter
        filteredGames.value = games.value?.filter { it.title.startsWith(newFilter) }
    }
    fun getFavGames(){
        filteredGames.value!!.filter { favGamesId.value.contains(it.id) }
    }
    fun retrieveEntireList(){
        filterString.value = ""
        filteredGames.value = games.value
    }
}

class GameViewModel : ViewModel() {
    val games = mutableStateOf<List<Game>?>(null)
    val game = mutableStateOf<Game?>(null)
    val favGamesId = mutableStateOf( database.gamesFavQueries.selectAll().executeAsList() )

    init{
        viewModelScope.launch {
            games.value = GamesApi.list()
        }
    }

    fun getGameWithId(gameId: Int){
        game.value = games.value?.first { it.id == gameId }
    }

    fun addToFavGames(newGameId: Int){
        favGamesId.value = database.gamesFavQueries.selectAll().executeAsList()
        database.gamesFavQueries.insert(newGameId.toLong())
    }
    fun removeFromFavGames(gameIdToRemove: Int){
        favGamesId.value = database.gamesFavQueries.selectAll().executeAsList()
        database.gamesFavQueries.delete(gameIdToRemove.toLong())
    }
}