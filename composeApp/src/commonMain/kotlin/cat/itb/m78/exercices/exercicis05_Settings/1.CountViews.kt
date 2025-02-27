package cat.itb.m78.exercices.exercicis05_Settings

/*
dintre del build.gradle.kts de composeApp, a commonMain.dependencies s'ha de possar:
implementation("com.russhwolf:multiplatform-settings-no-arg:1.3.0")
implementation("com.russhwolf:multiplatform-settings-serialization:1.3.0")
*/

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

private const val COUNT_VIEW_KEY = "count"
class CountViewViewModel : ViewModel(){
    val settings: Settings = Settings()
    val countViews = settings.getInt(COUNT_VIEW_KEY, 0)
    init {
        settings[COUNT_VIEW_KEY] = countViews+1
    }
}

@Composable
fun CountViewViewScreen(){
    val viewModel = viewModel { CountViewViewModel() }
    CountView(viewModel.countViews)
}

@Composable
fun CountView(countViews: Int) {
    Text("You have opened this app $countViews times")
}