package cat.itb.m78.exercices.exercicis06_Api

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import kotlinx.datetime.DateTimeUnit
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Embassament(
    @SerialName ("dia") val date: DateTimeUnit,
    @SerialName  ("estaci") val name: String,
    @SerialName ("nivell_absolut") val level: Double,
    @SerialName ("percentatge_volum_embassat") val volumePercent: Double,
    @SerialName ("volum_embassat") val volume: Double,
)

object EmbassamentApi{
    private val url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Embassament>>()
}

class EmvassamentViewModel : ViewModel(){
    val embassaments = mutableStateOf<List<Embassament>>( value = TODO() )
    init{
        viewModelScope.launch(Dispatchers.Default){
            embassaments.value = EmbassamentApi.list()
        }
    }
}

@Composable
fun EmbassamentScreen(){
    val viewModel = viewModel{ EmvassamentViewModel() }
    LlistaEmbassaments(viewModel.embassaments.value)
}

@Composable
fun LlistaEmbassaments(list: List<Embassament>){
    for (i in 0..list.size){
        Row {
            Text(list[i].name)
            Button( onClick = {
                LecturesEmbassament(list[i])
            }){
                Text("Veure les lectures")
            }
        }
    }
}

@Composable
fun LecturesEmbassament(embassament: Embassament){
    Text("${embassament.name} \n " +
            "\n Data de la lectura: ${embassament.date} " +
            "\nNivell absolut: ${embassament.level} " +
            "\nVolum embassament: ${embassament.volume} Percentatge del volum de l'embassament: ${embassament.volumePercent}")
}