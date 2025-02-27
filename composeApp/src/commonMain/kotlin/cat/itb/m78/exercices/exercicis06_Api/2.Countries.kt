package cat.itb.m78.exercices.exercicis06_Api

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Countrie(
    @SerialName("name") val name: String,
    @SerialName("capital") val capital: String
)
/*
@Composable
fun DisplayCountries(){
    var countriesList = ""
    val countries : List<Countrie>
    countries.forEach {
        countriesList += "\n Nom: ${it.name} Capital: ${it.capital}"
    }
    Text(countriesList)
}
*/