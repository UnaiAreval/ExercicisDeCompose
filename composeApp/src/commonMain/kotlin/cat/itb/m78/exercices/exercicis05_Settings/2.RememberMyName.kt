package cat.itb.m78.exercices.exercicis05_Settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

private const val REMEMBER_NAME_KEY = "name"
class RememberNameViewModel : ViewModel(){
    val settings: Settings = Settings()
    val rememberName = settings.getString(REMEMBER_NAME_KEY, "")
}

@Composable
fun RememberNameViewScreen() {
    val viewModel = viewModel { RememberNameViewModel() }
    val newName = remember { mutableStateOf("") }
    val thereIsNoName = remember { mutableStateOf(false) }
    Column {
        TextField(
            value = newName.value,
            label = { Text(text = "") },
            onValueChange = { newName.value = it }
        )

        if (thereIsNoName.value){
            Text("You need to introduce a name")
        }

        Button(onClick = {
            if (newName.value != "") {
                thereIsNoName.value = false
                viewModel.settings[REMEMBER_NAME_KEY] = newName.value
            } else{
                thereIsNoName.value = true
            }
        }){
            Text("Guardar nom")
        }
        Text("Hello ${viewModel.rememberName}")
    }
}