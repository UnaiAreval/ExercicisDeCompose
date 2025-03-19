package cat.itb.m78.exercices.exercicis06_Api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.datetime.Month
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

//Hi ha alguna cosa que fa que no funcioni, però no marca cap error. S'ha de revissar

@Serializable
data class Embassament(
    @SerialName ("dia") val date: String,
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
    val embassaments = mutableStateOf<List<Embassament>?>( null )
    init{
        viewModelScope.launch(Dispatchers.Default){
            embassaments.value = EmbassamentApi.list()
        }
    }
}

object ScreenDestination {
    @Serializable
    data object ListEmbassaments
    @Serializable
    data class InfoEmbassament(val embassamentName: String)
}

@Composable
fun EmbassamentScreen(){
    val viewModel = viewModel{ EmvassamentViewModel() }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenDestination.ListEmbassaments) {
        composable<ScreenDestination.ListEmbassaments> {
            LlistaEmbassaments(
                viewModel.embassaments.value,
                navigateToInfoEmbassament = { navController.navigate(ScreenDestination.InfoEmbassament(it)) }
            )
        }
        composable<ScreenDestination.InfoEmbassament> {backStack ->
            val embassament = backStack.toRoute<ScreenDestination.InfoEmbassament>().embassamentName
            InfoEmbassament(
                embassament,
                navigateToListEmbassaments = { navController.navigate(ScreenDestination.ListEmbassaments) }
            )
        }
    }
}

@Composable
fun LlistaEmbassaments(list: List<Embassament>?, navigateToInfoEmbassament: (String)-> Unit){
    if (list != null) {
        for (i in 0..list.size - 2) {
            for (j in 1..<list.size){
                if (list[i].name == list[j].name){

                }
            }
        }

        LazyColumn (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            for (i in list.indices) {
                item {
                    Column (modifier = Modifier.padding(10.dp)) {
                        Text(list[i].name)
                        Button(onClick = {
                            navigateToInfoEmbassament(list[i].name)
                        }) {
                            Text("Veure les lectures")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoEmbassament(embassamentName: String, navigateToListEmbassaments: ()-> Unit){
    val list : List<Embassament>
    Column {
        /*
        LazyColumn (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            for (i in list.indices) {
                item {
                    Column (modifier = Modifier.padding(10.dp)) {
                        Text("${list[i].name} \n " +
                                "\n Data de la lectura: ${list[i].date} " +
                                "\nNivell absolut: ${list[i].level} " +
                                "\nVolum embassament: ${list[i].volume} Percentatge del volum de l'embassament: ${list[i].volumePercent}")
                    }
                }
            }
        }
        */
        Button(onClick = {
            navigateToListEmbassaments()
        }){
            Text("Tornar a la pantalla principal")
        }
    }
}
/*
object InfoEmbassamentApi{
    val url = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json?estaci=$embassamentName"
    val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Embassament>>()
}
*/