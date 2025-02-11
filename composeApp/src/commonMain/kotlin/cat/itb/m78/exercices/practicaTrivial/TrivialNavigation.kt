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
        )
        }
        composable<TrivialDestination.SettingsScreen> { SettingsScreen () }
        composable<TrivialDestination.QuestionsScreen>{ QuestionNav() }
    }
}

private class ManualNavAppViewModel : ViewModel() {
    val currentQuestion = mutableStateOf<Question>(Question.Question1(points = 0))
    fun navigateTo(question: Question) {
        currentQuestion.value = question
    }
}
//Manera simple
data class MyQuestion(
    val unit : String,
    val askFor : String,
    val answer1 : String,
    val answer2 : String,
    val answer3 : String,
    val answer4 : String,
    val clueItsYourAnswerCorrect : String,
    val correctOne : Int
)
val questions = listOf(
    MyQuestion(//Question 1
        unit = "Science-Biology",
        askFor = "Which of this animals is not an arachnid?",
        answer1 = "Scorpion",
        answer2 = "Megarachne",
        answer3 = "Tick",
        answer4 = "All of them are arachnids",
        clueItsYourAnswerCorrect = "Sometimes names are deceiving",
        correctOne = 2
    ),
    MyQuestion(//Question 2
        unit = "WarHammer 40.000",
        askFor = "Which is the characteristic of a purple ork",
        answer1 = "They are faster",
        answer2 = "They are lucky",
        answer3 = "They explode better",
        answer4 = "They are invisible",
        clueItsYourAnswerCorrect = "Have you ever seen a purple ork?",
        correctOne = 4
    ),
    MyQuestion(//Question 3
        unit = "DOOM",
        askFor = "Which of the following demons can bee a problem for the Slayer?",
        answer1 = "Cyberdemon",
        answer2 = "Marauder",
        answer3 = "Any of them",
        answer4 = "Icon of Sin",
        clueItsYourAnswerCorrect = "The Slayer is undefeatable",
        correctOne = 3
    ),
    MyQuestion(//Question 4
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 2
    ),
    MyQuestion(//Question 5
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 3
    ),
    MyQuestion(//Question 6
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 1
    ),
    MyQuestion(//Question 7
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 1
    ),
    MyQuestion(//Question 8
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 4
    ),
    MyQuestion(//Question 9
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 2
    ),
    MyQuestion(//Question 10
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 3
    )
)
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
    data class Result(val points: Int) : Question
    data object ReturnToMain : Question
}

@Composable
fun QuestionNav(){
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentQuestion = viewModel.currentQuestion.value
    when (currentQuestion) {
        is Question.Question1 -> QuestionScreen(
            currentQuestion.points,
            navigateToNextQuestion = { viewModel.navigateTo(Question.Question2(it)) },
            unit = "Science-Biology",
            askFor = "Which of this animals is not an arachnid?",
            answer1 = "Scorpion",
            answer2 = "Megarachne",
            answer3 = "Tick",
            answer4 = "All of them are arachnids",
            clueItsYourAnswerCorrect = "Sometimes names are deceiving",
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
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            clueItsYourAnswerCorrect = "",
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
            clueItsYourAnswerCorrect = "",
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
            clueItsYourAnswerCorrect = "",
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
            unit = "",
            askFor =  "",
            answer1 = "",
            answer2 = "",
            answer3 = "",
            answer4 = "",
            clueItsYourAnswerCorrect = "",
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
            clueItsYourAnswerCorrect = "",
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
            clueItsYourAnswerCorrect = "",
            correctOne = 3
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
            clueItsYourAnswerCorrect = "",
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
            navigateToNextQuestion = { viewModel.navigateTo(Question.Result(it)) },
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
            currentQuestion.points
        )
        is Question.ReturnToMain -> TrivialNavigateSample()
    }
}