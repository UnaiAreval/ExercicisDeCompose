package cat.itb.m78.exercices.exercicis06_Api

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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Countrie(
    @SerialName("name") val name: String,
    @SerialName("capital") val capital: String
)

object CountrieApi{
    private val url = "https://api.sampleapis.com/countries/countries"
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Countrie>>()
}

class CountriesViewModel() : ViewModel(){
    val countries = mutableStateOf<List<Countrie>>(
        value = TODO()
    )
    init{
        viewModelScope.launch(Dispatchers.Default){
            countries.value = CountrieApi.list()
        }
    }
}

@Composable
fun CountrieScreen(){
    val viewModel = viewModel {CountriesViewModel()}
    DisplayCountries(viewModel.countries.value)
}

@Composable
fun DisplayCountries(countries : List<Countrie>){
    var countriesText = "Llista de ciutats: \n "

    countries.forEach {
        countriesText += "\n Nom: ${it.name} Capital: ${it.capital}"
    }

    Text(countriesText)
}
