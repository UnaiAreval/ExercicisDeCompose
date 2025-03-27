package cat.itb.m78.exercices.practicaAPI_DB

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.Platform
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

//"Enllas de la API https://www.freetogame.com/api/games"

@Serializable
data class Game(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("thumbnail") val imageURL: String,
    @SerialName("short_description") val shortDescription: String,
    @SerialName("game_url")val gameURL: String,
    @SerialName("genre") val genre: String,
@SerialName("platform") val platform: String,
@SerialName("publisher") val publisher: String,
@SerialName("developer") val developer: String,
@SerialName("release_date") val releaseDate: String,
@SerialName("freetogame_profile_url") val freetogameProfileUrl: String
)

object GamesApi{
    private val url ="https://www.freetogame.com/api/games"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Game>>()
}

object GamesNavigation{
    @Serializable
    data object MainGamesList
    @Serializable
    data object FavoriteGamesList
    @Serializable
    data class GameInfomation(val gameId: Int)
}

class GamesListViewModel : ViewModel(){
    val games = mutableStateOf<List<Game>?>(null)
    init {
        viewModelScope.launch {
            games.value = GamesApi.list()
        }
    }
}

@Composable
fun GamesScreen(){
    val viewModel = viewModel { GamesListViewModel() }
}

@Composable
fun GamesMainScreen(games: List<Game>){
    LazyColumn {
        for (game in games){
            item {
                Text("")
            }
        }
    }
}