package cat.itb.m78.exercices.exercicis06_Api

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.random.Random

@Serializable
data class Joke(
    @SerialName("setup") val setup: String,
    @SerialName("punchline") val punchline: String
)
object JokesApi{
    private val url = "https://api.sampleapis.com/jokes/goodJokes"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Joke>>()
}

class JokeViewModel() : ViewModel(){
    val data = mutableStateOf<List<Joke>>(
        value = TODO()
    )
    init{
        viewModelScope.launch(Dispatchers.Default){
            data.value = JokesApi.list()
        }
    }
}

@Composable
fun JokeScreen(){
    val viewModel = viewModel { JokeViewModel() }
    PrintJoke(viewModel.data.value)
}

@Composable
fun PrintJoke(jokes: List<Joke>){
    val jokeNum : Int = Random.nextInt(0, jokes.size - 1)
    val joke :Joke = jokes[jokeNum]

    println("${joke.setup} \n ${joke.punchline}")
}