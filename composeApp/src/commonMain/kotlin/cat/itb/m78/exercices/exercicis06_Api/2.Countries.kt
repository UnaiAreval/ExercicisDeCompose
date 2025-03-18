package cat.itb.m78.exercices.exercicis06_Api

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import coil3.size.Size
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
data class Media(
    @SerialName("flag") val flag: String
)

@Serializable
data class Countrie(
    @SerialName("name") val name: String,
    @SerialName("capital") val capital: String,
    @SerialName("media") val media: Media
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
    val countries = mutableStateOf<List<Countrie>?>( null )
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
fun DisplayCountries(countries : List<Countrie>?){
    val showedCountries = remember { mutableStateOf(arrayOf(0, 10)) }
    val numPage = remember { mutableStateOf(1) }

    if (countries != null){
        Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center ) {

            Column {
                Text("Llista de paisos Pagina ${numPage.value}")
                for ( i in showedCountries.value[0]..showedCountries.value[1] ){
                    Row  {
                        Text("\n Nom: ${countries[i].name} Capital: ${countries[i].capital}")
                        AsyncImage(
                            model = countries[i].media.flag,
                            contentDescription = "countrie flag",
                            modifier = Modifier.size(50.dp, 25.dp)
                        )
                    }
                }
            }

            Row {
                Button(onClick = {
                    if (showedCountries.value[0] - 10 < 0) {
                        showedCountries.value = arrayOf(0, 10)
                    }
                    else if (showedCountries.value[1] != countries.size - 1){
                        showedCountries.value = arrayOf(
                            showedCountries.value[0] - 10,
                            showedCountries.value[0]
                        )
                        numPage.value -= 1
                    }
                    else {
                        showedCountries.value = arrayOf(
                            showedCountries.value[0] - 10,
                            showedCountries.value[1] - 10
                        )
                        numPage.value -= 1
                    }
                }){
                    Text("Previous")
                }
                Button(onClick = {
                    if (showedCountries.value[1] != countries.size - 1) {
                        if (showedCountries.value[1] + 10 >= countries.size) {
                            showedCountries.value =
                                arrayOf(showedCountries.value[1], countries.size - 1)
                            numPage.value += 1
                        } else {
                            showedCountries.value = arrayOf(
                                showedCountries.value[0] + 10,
                                showedCountries.value[1] + 10
                            )
                            numPage.value += 1
                        }
                    }
                }){
                    Text("Next")
                }
            }

        }
    }
}
