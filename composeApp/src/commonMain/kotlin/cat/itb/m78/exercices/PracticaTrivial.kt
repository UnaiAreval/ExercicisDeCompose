package cat.itb.m78.exercices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object TrivialDestination{
    @Serializable
    data object MainScreen
    @Serializable
    data class SettingsScreen(val rounds: Int)
    @Serializable
    data class QuestionScreen1(var points: Int)
    @Serializable
    data class QuestionScreen2(var points: Int)
}

@Composable
fun TrivialNavigateSample(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TrivialDestination.MainScreen) {
        composable<TrivialDestination.MainScreen> { TrivialMenu(
            navigateToQuestion1 = { navController.navigate(TrivialDestination.QuestionScreen1(it)) }
        )}
        composable<TrivialDestination.SettingsScreen> { SettingsScreen () }
        composable<TrivialDestination.QuestionScreen1> { backStack ->
            val points = backStack.toRoute<TrivialDestination.QuestionScreen1>().points
            QuestionScreen1(
                navigateToNextQuestion = { navController.navigate(TrivialDestination.QuestionScreen2(it)) },
                points
            )
        }
        composable<TrivialDestination.QuestionScreen2> { backStack ->
            val points = backStack.toRoute<TrivialDestination.QuestionScreen2>().points
            QuestionScreen2(
                navigateToNextQuestion = { navController.navigate(TrivialDestination.QuestionScreen1(it)) },
                points
            )
        }
    }
}

@Composable
fun TrivialMenu(
    navigateToQuestion1: (Int)-> Unit
){
    val rounds = remember { mutableStateOf(5) }
    val difficulty = remember { mutableStateOf("") }
    val points = 0

    Column {
        Button(onClick = { navigateToQuestion1(points) })
        {Text("Start Game") }
        Button(onClick = {

        }){
            Text("Settings")
        }

        Row {
            if (rounds.value != 5 || rounds.value != 10 || rounds.value != 15){
                Text("Number of rounds: " + rounds.value)
                Text("Difficulty: Easy")
            } else {
                Text("Number of rounds: " + rounds)
                Text("Difficulty: " + difficulty)
            }
        }
    }
}

@Composable
fun SettingsScreen(){
    var newRounds: Int

    Column {
        Row {

            Column {
                Text("Select rounds amount: ")
                Button(onClick = { newRounds = 5 }){ Text("5 rounds") }
                Button(onClick = {newRounds = 10}){ Text("10 rounds") }
                Button(onClick = {newRounds = 15}){ Text("15 rounds") }
            }
        }
        Button(onClick = {  }){
            Text("Return to the game menu")
        }
    }
}

@Composable
fun QuestionScreen1(
    navigateToNextQuestion: (Int)-> Unit,
    points: Int){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Science-Biology", modifier = Modifier.padding(20.dp))
        Text("Which of this animals is not an arachnid?")
        Row {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { navigateToNextQuestion(points) }){
                    Text("Scorpion") //Wrong Option
                }
                Button(onClick = { navigateToNextQuestion(points + 1) }){
                    Text("Megarachne") //Good Option
                }
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { navigateToNextQuestion(points) }){
                    Text("Tick") //Wrong Option
                }
                Button(onClick = { navigateToNextQuestion(points) }){
                    Text("All of them are arachnids") //Wrong Option
                }
            }
        }
    }
}

@Composable
fun QuestionScreen2(
    navigateToNextQuestion: (Int)-> Unit,
    points: Int){

}