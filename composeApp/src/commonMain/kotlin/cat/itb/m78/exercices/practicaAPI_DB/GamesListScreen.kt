package cat.itb.m78.exercices.practicaAPI_DB

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil3.compose.AsyncImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.heartIcon
import m78exercices.composeapp.generated.resources.house
import org.jetbrains.compose.resources.painterResource

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

data class GameFav(
    val game: Game,
    val fav: Boolean
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

object GamesNavigation{
    @Serializable
    data object MainGamesList
    @Serializable
    data class GameInfomation(val gameId: Int)
}

@Composable
fun GamesScreen(){
    val viewModel = viewModel { GamesViewModel() }
    val navController = rememberNavController()

    Scaffold (bottomBar = {
        BottomAppBar (
            actions = {
                NavigationBarItem(
                    onClick = {

                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.heartIcon),
                            contentDescription = "Favourite Games",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text(text = "Fav Games")
                    }
                )
                NavigationBarItem(
                    onClick = {
                        viewModel.retrieveEntireList()
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.house),
                            contentDescription = "Home List",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text("Get hole list")
                    }
                )
            }, containerColor = Color.Blue
        )
    }) {
        NavHost(modifier = Modifier.background(color = Color.Cyan),
            navController = navController, startDestination = GamesNavigation.MainGamesList){
            composable<GamesNavigation.MainGamesList> {
                GamesMainScreen(
                    viewModel.filteredGames.value,
                    viewModel.filterString.value,
                    viewModel::onUpdateFilter,
                    goToGame = { navController.navigate(GamesNavigation.GameInfomation(it)) }
                )
            }
            composable<GamesNavigation.GameInfomation> { backStack ->
                val gameId = backStack.toRoute<GamesNavigation.GameInfomation>().gameId
                GameInformation(
                    gameId,
                    games = viewModel.games.value!!,
                    goBack = { navController.navigate(GamesNavigation.MainGamesList) }
                )
            }
        }
    }
}

@Composable
fun GamesMainScreen(
    gamesToDisplay: List<Game>?,
    filterString: String,
    onUpdateFilter: (String) -> Unit,
    goToGame: (Int) -> Unit
){

    if (gamesToDisplay != null) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {

            Row (modifier = Modifier.background(color = Color.Blue).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center){
                TextField(
                    value = filterString,
                    label = { Text(text = "") },
                    onValueChange = { onUpdateFilter(it) },
                    modifier = Modifier.padding(10.dp)
                )
            }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                for (game in gamesToDisplay) {
                    item {
                        Spacer(modifier = Modifier.size(30.dp))
                    }
                    item {
                        Row {
                            Column {
                                Text(game.title)
                                Text("by " + game.developer)
                            }
                            Spacer(modifier = Modifier.size(100.dp))
                            Button(onClick = {
                                goToGame(game.id)
                            }) {
                                Text("Go to " + game.title)
                            }
                        }
                    }
                }
            }
            //Consultar per fer una barra de cerca inferior
            //https://m2.material.io/components/bottom-navigation/android#bottom-navigation-bar
        }
    }
}

@Composable
fun GameInformation(gameId: Int, games: List<Game>, goBack: () -> Unit){
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        for (g in games){
            if (gameId == g.id) {
                item {
                    Column (modifier = Modifier.fillMaxWidth().background(color = Color.Blue), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(g.title, fontWeight = FontWeight(900), fontSize = 30.sp)
                        Text("Made by: " + g.developer, fontWeight = FontWeight(600), fontSize = 15.sp)
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    AsyncImage(
                        model = g.imageURL,
                        contentDescription = "game image",
                        modifier = Modifier.size(400.dp, 200.dp)
                    )
                    Spacer(modifier = Modifier.size(40.dp))
                    Text("General Info: ", fontWeight = FontWeight(600), fontSize = 20.sp)
                    Text("Publisher: ${ g.publisher } \nRelease date: ${g.releaseDate} \nGenre: ${g.genre} \nPlatform: ${g.platform}")
                    Spacer(modifier = Modifier.size(20.dp))
                    Text("Description: \n" + g.shortDescription)
                }
                item {
                    Button(onClick = { goBack() }, modifier = Modifier.padding(horizontal = 75.dp, vertical = 10.dp)){
                        Text("Go back")
                    }
                    Spacer(modifier = Modifier.size(100.dp))
                }
            }
        }
    }
}