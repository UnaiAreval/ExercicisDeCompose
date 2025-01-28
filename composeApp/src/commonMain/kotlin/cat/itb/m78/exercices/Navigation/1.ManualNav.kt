package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ManualNav(){
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentScreen = viewModel.currentScreen.value
    when (currentScreen) {
        Screen.MainScreen -> MainScreen(
            navigateToScreen1 = { viewModel.navigateTo(Screen.Screen1) },
            navigateToScreen2 = { viewModel.navigateTo(Screen.Screen2) },
            navigateToScreen3 = { viewModel.navigateTo(Screen.Screen3(it))}
        )
        is Screen.Screen1 -> Screen1(
            navigateToMainManualNav = { viewModel.navigateTo(Screen.MainScreen) }
        )
        is Screen.Screen2 -> Screen2(
            navigateToMainManualNav = { viewModel.navigateTo(Screen.MainScreen) }
        )
        is Screen.Screen3 -> Screen3(
            currentScreen.message,
            navigateToMainManualNav = { viewModel.navigateTo(Screen.MainScreen) }
        )
    }
}
private sealed interface Screen {
    data object MainScreen : Screen
    data object Screen1 : Screen
    data object Screen2 : Screen
    data class Screen3(val message: String) : Screen
}
private class ManualNavAppViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.MainScreen)
    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }
}

@Composable
fun MainScreen(navigateToScreen1: ()-> Unit, navigateToScreen2: ()-> Unit, navigateToScreen3: (String)-> Unit){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        Button(onClick = { navigateToScreen1() })
        { Text("Screen 1") }

        Button(onClick = { navigateToScreen2()})
        { Text("Screen 2") }

        Button(onClick = { navigateToScreen3("Hello") })
        { Text("Screen 3 - Hello") }

        Button(onClick = { navigateToScreen3("Bye") })
        { Text("Screen 3 - Bye") }

    }
}
@Composable
fun Screen1(navigateToMainManualNav: ()-> Unit){
    Column (modifier = Modifier.background(color = Color.Green).fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom){

        Text("Screen 1")
        Button(onClick = navigateToMainManualNav){
            Text("Back to main menu")
        }

    }
}
@Composable
fun Screen2(navigateToMainManualNav: ()-> Unit){
    Column (modifier = Modifier.background(color = Color.Red).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){

        Text("Screen 2")
        Button(onClick = navigateToMainManualNav){
            Text("Back to main menu")
        }

    }
}
@Composable
fun Screen3(message: String, navigateToMainManualNav: ()-> Unit){
    Column (modifier = Modifier.background(color = Color.Blue).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text("Screen 3")
        Text(message)
        Button(onClick = navigateToMainManualNav){
            Text("Back to main menu")
        }

    }
}