package cat.itb.m78.exercices

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.painterResource

object TrivialDestination{
    @Serializable
    data object MainScreen
    @Serializable
    data class SettingsScreen(val rounds: Int)
    @Serializable
    data class QuestionsScreen(var points: Int)
}

@Composable
fun TrivialNavigateSample(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TrivialDestination.MainScreen) {
        composable<TrivialDestination.MainScreen> { TrivialMenu(
            navigateToQuestions = { navController.navigate(TrivialDestination.QuestionsScreen(it)) }
        )}
        composable<TrivialDestination.SettingsScreen> { SettingsScreen () }
        composable<TrivialDestination.QuestionsScreen>{ backStack ->
            val points = backStack.toRoute<TrivialDestination.QuestionsScreen>().points
            QuestionNav(
            points
        ) }
    }
}

@Composable
fun TrivialMenu(
    navigateToQuestions: (Int)-> Unit
){
    val rounds = remember { mutableStateOf(5) }
    val difficulty = remember { mutableStateOf("") }
    val points = 0

    Column (modifier = Modifier.fillMaxSize().background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(
            painter = painterResource(Res.drawable.trivial),
            modifier = Modifier.size(150.dp).padding(15.dp).clip(CircleShape),
            contentDescription = null
        )
        Button(onClick = { navigateToQuestions(points) })
        {Text("Start Game") }
        Button(onClick = {

        }){
            Text("Settings")
        }

        Row {
            if (rounds.value != 5 || rounds.value != 10 || rounds.value != 15){
                Text("Number of rounds: " + rounds.value, modifier = Modifier.padding(10.dp))
                Text("Difficulty: Easy", modifier = Modifier.padding(10.dp))
            } else {
                Text("Number of rounds: " + rounds, modifier = Modifier.padding(10.dp))
                Text("Difficulty: " + difficulty, modifier = Modifier.padding(10.dp))
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
fun QuestionScreen(
    points: Int,
    navigateToNextQuestion: (Int) -> Unit,
    unit: String,
    askFor: String,
    answer1: String,
    answer2: String,
    answer3: String,
    answer4: String,
    correctOne: Int
){
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(unit, modifier = Modifier.padding(20.dp))

        Image(
            painter = painterResource(Res.drawable.trivial),
            modifier = Modifier.size(150.dp).padding(15.dp),
            contentDescription = null
        )

        Text(askFor)
        Row {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    if (correctOne == 1){
                        navigateToNextQuestion(points + 1)
                    } else {
                        navigateToNextQuestion(points)
                    }
                },
                    modifier = Modifier.background(Color.Red).size(200.dp, 75.dp).padding(10.dp)){
                    Text(answer1, textAlign = TextAlign.Center)
                }
                Button(onClick = {
                    if (correctOne == 3){
                        navigateToNextQuestion(points + 1)
                    } else {
                        navigateToNextQuestion(points)
                    }
                },
                    modifier = Modifier.background(Color.Yellow).size(200.dp, 75.dp).padding(10.dp)){
                    Text(answer3, textAlign = TextAlign.Center)
                }
            }
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = {
                    if (correctOne == 2){
                        navigateToNextQuestion(points + 1)
                    } else {
                        navigateToNextQuestion(points)
                    }
                },
                    modifier = Modifier.background(Color.Blue).size(200.dp, 75.dp).padding(10.dp)){
                    Text(answer2, textAlign = TextAlign.Center)
                }
                Button(onClick = {
                    if (correctOne == 4){
                        navigateToNextQuestion(points + 1)
                    } else {
                        navigateToNextQuestion(points)
                    }
                },
                    modifier = Modifier.background(Color.Green).size(200.dp, 75.dp).padding(10.dp)){
                    Text(answer4, textAlign = TextAlign.Center)
                }
            }
        }
        Text("Your points $points")
    }
}

private sealed interface Question {
    data class baseQuestion(val points: Int) : Question
    data class Question2(val points: Int) : Question
    data class Question3(val points: Int) : Question
    data class Question4(val points: Int) : Question
    data class Question5(val points: Int) : Question
    data class Question6(val points: Int) : Question
    data class Question7(val points: Int) : Question
    data class Question8(val points: Int) : Question
    data class Question9(val points: Int) : Question
    data class Question10(val points: Int) : Question
    data class Question11(val points: Int) : Question
    data class Question12(val points: Int) : Question
    data class Question13(val points: Int) : Question
    data class Question14(val points: Int) : Question
    data class Question15(val points: Int) : Question
}

private class ManualNavAppViewModel : ViewModel() {
    val currentQuestion = mutableStateOf<Question>(Question.baseQuestion(points = 0))
    fun navigateTo(question: Question) {
        currentQuestion.value = question
    }
}

@Composable
fun QuestionNav(points: Int){
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentQuestion = viewModel.currentQuestion.value
    when (currentQuestion) {
        is Question.baseQuestion -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question2(it)) },
            unit = "Science-Biology",
            askFor =  "Which of this animals is not an arachnid?",
            answer1 = "Scorpion",
            answer2 = "Megarachne",
            answer3 = "Tick",
            answer4 = "All of them are arachnids",
            correctOne = 2
        )

        is Question.Question2 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question3(it)) },
            unit = "WarHammer 40.000",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 1
        )
        is Question.Question3 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question4(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 3
        )
        is Question.Question4 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question5(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question5 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question6(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 3
        )
        is Question.Question6 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question7(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 1
        )
        is Question.Question7 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question8(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 1
        )
        is Question.Question8 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question9(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 4
        )
        is Question.Question9 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question10(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question10 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question11(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question11 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question12(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question12 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question13(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question13 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question14(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question14 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question15(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
        is Question.Question15 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question15(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            correctOne = 2
        )
    }
}