package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

object TrivialDestination{
    @Serializable
    data object MainScreen
    @Serializable
    data object SettingsScreen
    @Serializable
    data object QuestionsScreen
}

var rounds = 5

@Composable
fun TrivialNavigateSample(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TrivialDestination.MainScreen) {
        composable<TrivialDestination.MainScreen> {
            TrivialMenu(
                navigateToSettings = { navController.navigate((TrivialDestination.SettingsScreen)) },
                navigateToQuestions = { navController.navigate(TrivialDestination.QuestionsScreen) }
            )
        }
        composable<TrivialDestination.SettingsScreen> { SettingsScreen (
            navigateBackToMenu = { navController.navigate(TrivialDestination.MainScreen) }
        ) }
        composable<TrivialDestination.QuestionsScreen>{ QuestionNav() }
    }
}

private class ManualNavAppViewModel : ViewModel() {
    val currentQuestion = mutableStateOf<Question>(Question.ListQuestion(points = 0))
    fun navigateTo(question: Question) {
        currentQuestion.value = question
    }
}

//Me he complicado la vida
private sealed interface Question {
    data class ListQuestion(val points: Int) : Question
    data class Result(val points: Int) : Question
    data object ReturnToMain : Question
}

@Composable
fun QuestionNav(){
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentQuestion = viewModel.currentQuestion.value
    when (currentQuestion) {
        is Question.ListQuestion -> QuestionUsingList(
            navigateToResultScreen = { viewModel.navigateTo(Question.Result(it)) }
        )
        is Question.Result -> ResultScreen(
            navigateToTrivialMenu = { viewModel.navigateTo(Question.ReturnToMain) },
            currentQuestion.points
        )
        is Question.ReturnToMain -> TrivialNavigateSample()
    }
}