package cat.itb.m78.exercices.practicaAPI_DB

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class GamesViewModel : ViewModel() {
    val games = mutableStateOf<List<Game>?>(null)
    val filteredGames = mutableStateOf<List<Game>?>(null)

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
    fun retrieveEntireList(){
        filterString.value = ""
        filteredGames.value = games.value
    }
}

class GameViewModel : ViewModel() {
    val games = mutableStateOf<List<Game>?>(null)
    val game = mutableStateOf<Game?>(null)

    init{
        viewModelScope.launch {
            games.value = GamesApi.list()
        }
    }

    fun getGameWithId(gameId: Int){
        game.value = games.value?.first { it.id == gameId }
    }
}