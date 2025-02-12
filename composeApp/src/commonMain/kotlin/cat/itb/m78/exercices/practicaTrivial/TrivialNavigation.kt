package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object TrivialDestination{
    @Serializable
    data class MainScreen(var rounds: Int)
    @Serializable
    data object SettingsScreen
    @Serializable
    data class QuestionsScreen(var rounds: Int)
}

@Composable
fun TrivialNavigateSample(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TrivialDestination.MainScreen(5)) {
        composable<TrivialDestination.MainScreen> {  backStack ->
            val rounds = backStack.toRoute<TrivialDestination.MainScreen>().rounds
            TrivialMenu(
                navigateToSettings = { navController.navigate((TrivialDestination.SettingsScreen)) },
                navigateToQuestions = { navController.navigate(TrivialDestination.QuestionsScreen(it)) },
                rounds
            )
        }
        composable<TrivialDestination.SettingsScreen> { SettingsScreen (
            navigateBackToMenu = { navController.navigate(TrivialDestination.MainScreen(it)) }
        ) }
        composable<TrivialDestination.QuestionsScreen>{ backStack ->
            val rounds = backStack.toRoute<TrivialDestination.QuestionsScreen>().rounds
            QuestionNav(rounds)
        }
    }
}

private class ManualNavAppViewModel : ViewModel() {
    val currentQuestion = mutableStateOf<Question>(Question.Question1(points = 0))
    fun navigateTo(question: Question) {
        currentQuestion.value = question
    }
}

//Me he complicado la vida
private sealed interface Question {
    data class Question1(val points: Int) : Question
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
    data class Result(val points: Int, val questionsDone: Int) : Question
    data object ReturnToMain : Question
}

@Composable
fun QuestionNav( rounds: Int ){
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentQuestion = viewModel.currentQuestion.value
    when (currentQuestion) {
        is Question.Question1 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question2(it)) },
            unit = "Science",
            askFor = "What temperature does water have to be to boil at sea level?",
            answer1 = "50ºC",
            answer2 = "100ºC",
            answer3 = "120ºC",
            answer4 = "39ºC",
            clueItsYourAnswerCorrect = "Water is the best drink, I'm '100'% sure",
            correctOne = 2
        )

        is Question.Question2 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question3(it)) },
            unit = "WarHammer 40.000",
            askFor = "Which is the characteristic of a purple ork",
            answer1 = "They are faster",
            answer2 = "They are lucky",
            answer3 = "They explode better",
            answer4 = "They are invisible",
            clueItsYourAnswerCorrect = "Have you ever seen a purple ork?",
            correctOne = 4
        )
        is Question.Question3 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question4(it)) },
            unit = "Hello Kitty",
            askFor = "What does Hello Kittys name mean?",
            answer1 = "Meaw meaw",
            answer2 = "Hello demon",
            answer3 = "Hola gatito",
            answer4 = "Puss in boots",
            clueItsYourAnswerCorrect = "You just need to translate it",
            correctOne = 3
        )
        is Question.Question4 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question5(it)) },
            unit = "Art or Science",
            askFor = "Which are the primary colors of light?",
            answer1 = "Magenta, Green and Cyan",
            answer2 = "Red, Green and Blue",
            answer3 = "Magenta, Yellow and Cyan",
            answer4 = "Red, Yellow and Blue",
            clueItsYourAnswerCorrect = "Yellow and Green change places depending if you ask for the colors of light and the primary pigment colors",
            correctOne = 2
        )
        is Question.Question5 -> if (rounds == 5){
            QuestionScreen(
                currentQuestion.points,
                navigateToNextQuestion = { viewModel.navigateTo(Question.Result(it, rounds)) },
                unit = "",
                askFor =  "",
                answer1 = "",
                answer2 = "",
                answer3 = "",
                answer4 = "",
                clueItsYourAnswerCorrect = "",
                correctOne = 3
            )
        } else{
            QuestionScreen(
                currentQuestion.points,
                navigateToNextQuestion = { viewModel.navigateTo(Question.Question6(it)) },
                unit = "",
                askFor =  "",
                answer1 = "",
                answer2 = "",
                answer3 = "",
                answer4 = "",
                clueItsYourAnswerCorrect = "",
                correctOne = 3
            )
        }
        is Question.Question6 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question7(it)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            clueItsYourAnswerCorrect = "",
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
            clueItsYourAnswerCorrect = "",
            correctOne = 1
        )
        is Question.Question8 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question9(it)) },
            unit = "Mythology",
            askFor =  "How will you call a dragon with two bat wings and two legs?",
            answer1 = "Chines dragon",
            answer2 = "Tarasca",
            answer3 = "Common dragon",
            answer4 = "Wyvern",
            clueItsYourAnswerCorrect = "The chines dragons doesn't have wings and the Tarasca it's also named like Cucafera. Also, the common dragons have fore legs.",
            correctOne = 4
        )
        is Question.Question9 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question10(it)) },
            unit = "Science-Biology",
            askFor = "Which of this animals is not an arachnid?",
            answer1 = "Scorpion",
            answer2 = "Megarachne",
            answer3 = "Tick",
            answer4 = "All of them are arachnids",
            clueItsYourAnswerCorrect = "Sometimes names are deceiving",
            correctOne = 2
        )
        is Question.Question10 -> if(rounds == 10){
            QuestionScreen(
                currentQuestion.points,
                navigateToNextQuestion = { viewModel.navigateTo(Question.Result(it, rounds)) },
                unit = "",
                askFor =  "",
                answer1 = "",
                answer2 = "",
                answer3 = "",
                answer4 = "",
                clueItsYourAnswerCorrect = "",
                correctOne = 3
            )
        } else{
            QuestionScreen(
                currentQuestion.points,
                navigateToNextQuestion = { viewModel.navigateTo(Question.Question11(it)) },
                unit = "",
                askFor =  "",
                answer1 = "",
                answer2 = "",
                answer3 = "",
                answer4 = "",
                clueItsYourAnswerCorrect = "",
                correctOne = 3
            )
        }
        is Question.Question11 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question12(it)) },
            unit = "Mythology",
            askFor =  "Which is between this gods and goddesses who is not a moon deity?",
            answer1 = "Selene",
            answer2 = "Thoth",
            answer3 = "Ilargi",
            answer4 = "Eguzkilore",
            clueItsYourAnswerCorrect = "Eguziklore was as shining as the sun",
            correctOne = 4
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
            clueItsYourAnswerCorrect = "",
            correctOne = 4
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
            clueItsYourAnswerCorrect = "",
            correctOne = 1
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
            clueItsYourAnswerCorrect = "",
            correctOne = 2
        )
        is Question.Question15 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Result(it, rounds)) },
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            clueItsYourAnswerCorrect = "",
            correctOne = 4
        )
        is Question.Result -> ResultScreen(
            navigateToTrivialMenu = { viewModel.navigateTo(Question.ReturnToMain) },
            currentQuestion.points,
            rounds
        )
        is Question.ReturnToMain -> TrivialNavigateSample()
    }
}