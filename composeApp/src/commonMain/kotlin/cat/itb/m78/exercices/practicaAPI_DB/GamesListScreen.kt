package cat.itb.m78.exercices.practicaAPI_DB

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import m78exercices.composeapp.generated.resources.search_icon
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
    val favGamesId = mutableStateOf<List<Int>?>(null)

    init {
        viewModelScope.launch {
            games.value = GamesApi.list()

        }
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
    NavHost(navController = navController, startDestination = GamesNavigation.MainGamesList){
        composable<GamesNavigation.MainGamesList> {
            GamesMainScreen(
                games = viewModel.games.value,
                favGamesId = viewModel.favGamesId.value,
                goToGame = { navController.navigate(GamesNavigation.GameInfomation(it)) }
            )
        }
        composable<GamesNavigation.GameInfomation> { backStack ->
            val gameId = backStack.toRoute<GamesNavigation.GameInfomation>().gameId
            GameInformation(
                gameId,
                viewModel.games.value!!,
                goBack = {navController.navigate(GamesNavigation.MainGamesList)}
            )
        }
    }
}

@Composable
fun GamesMainScreen(
    games: List<Game>?,
    favGamesId: List<Int>?,
    goToGame: (Int) -> Unit
){

    val displayJustFavOnes = remember { mutableStateOf(false) }
    val searcherText = remember { mutableStateOf("") }

    if (games != null) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {

            Row (modifier = Modifier.padding(20.dp)){
                Image(
                    painter = painterResource(Res.drawable.search_icon),
                    modifier = Modifier.size(50.dp).padding(5.dp),
                    contentDescription = null
                )
                TextField(
                    value = searcherText.value,
                    label = { Text(text = "") },
                    onValueChange = { searcherText.value = it }
                )
            }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                if (searcherText.value == "" && !displayJustFavOnes.value) {
                    for (game in games) {
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
                else if (searcherText.value == "" && displayJustFavOnes.value){
                    for (game in games) {
                        for (fgID in favGamesId!!) {
                            if (game.id == fgID) {
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
                    }
                }
                else {
                    for (game in games) {
                        if (game.title == searcherText.value || game.developer == searcherText.value || game.publisher == searcherText.value || game.platform == searcherText.value) {
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
            item {
                if (gameId == g.id) {
                    Text(g.title, fontWeight = FontWeight(900), fontSize = 30.sp)
                    Text("Made by: " + g.developer, fontWeight = FontWeight(600), fontSize = 15.sp)
                    Spacer(modifier = Modifier.size(30.dp))
                    AsyncImage(
                        model = g.imageURL,
                        contentDescription = "game image",
                        modifier = Modifier.size(400.dp, 200.dp)
                    )
                    Spacer(modifier = Modifier.size(40.dp))
                    Text(g.shortDescription)
                    Button(onClick = {
                        goBack()
                    }){
                        Text("Go back")
                    }
                }
            }
        }
    }
}