package cat.itb.m78.exercices.exercicis06_Api

import androidx.compose.runtime.Composable
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class Joke(
    @SerialName("setup") val setup: String,
    @SerialName("punchline") val punchline: String
)

@Composable
fun TellJokes(){
    val httpResponse:  HttpResponse

    val jokes : List<Joke> //= client.get("https://api.sampleapis.com/jokes/goodJokes").body()
    val jokeNum : Int //= Random.nextInt(0, jokes.size - 1)
    val joke :Joke //= jokes[jokeNum]

    //println("${joke.setup} \n ${joke.punchline}")
}