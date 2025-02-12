package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.trivial
import org.jetbrains.compose.resources.painterResource

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
    clueItsYourAnswerCorrect: String,
    correctOne: Int
){
    val questionAnswered = remember{ mutableStateOf(false) }
    val answeredWell = remember{ mutableStateOf(false) }
    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        if (!questionAnswered.value){
            Text(unit, modifier = Modifier.padding(20.dp))

            Image(
                painter = painterResource(Res.drawable.trivial),
                modifier = Modifier.size(150.dp).padding(15.dp),
                contentDescription = null
            )

            Text(askFor)
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            if (!questionAnswered.value) {
                                if (correctOne == 1) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            }
                        },
                        modifier = Modifier.background(Color.Red).size(200.dp, 75.dp)
                            .padding(10.dp)
                    ) {
                        Text(answer1, textAlign = TextAlign.Center)
                    }
                    Button(
                        onClick = {
                            if (!questionAnswered.value) {
                                if (correctOne == 3) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            }
                        },
                        modifier = Modifier.background(Color.Yellow).size(200.dp, 75.dp)
                            .padding(10.dp)
                    ) {
                        Text(answer3, textAlign = TextAlign.Center)
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            if (!questionAnswered.value) {
                                if (correctOne == 2) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            }
                        },
                        modifier = Modifier.background(Color.Blue).size(200.dp, 75.dp)
                            .padding(10.dp)
                    ) {
                        Text(answer2, textAlign = TextAlign.Center)
                    }
                    Button(
                        onClick = {
                            if (!questionAnswered.value){
                                if (correctOne == 4) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            }
                        },
                        modifier = Modifier.background(Color.Green).size(200.dp, 75.dp)
                            .padding(10.dp)
                    ) {
                        Text(answer4, textAlign = TextAlign.Center)
                    }
                }
            }
        } else{
            Text(clueItsYourAnswerCorrect, textAlign = TextAlign.Center)
            Button(onClick = {
                if (answeredWell.value){
                    navigateToNextQuestion(points + 1)
                } else{
                    navigateToNextQuestion(points)
                }
            }){
                Text("Next Question")
            }
        }
        if (answeredWell.value){
            Text("Your points ${points + 1}", textAlign = TextAlign.Center)
        } else{
            Text("Your points $points", textAlign = TextAlign.Center)
        }
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
    ),
    MyQuestion(//Question 11
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 1
    ),
    MyQuestion(//Question 12
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 1
    ),
    MyQuestion(//Question 13
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 4
    ),
    MyQuestion(//Question 14
        unit = "",
        askFor =  "",
        answer1 = "",
        answer2 = "",
        answer3 = "",
        answer4 = "",
        clueItsYourAnswerCorrect = "",
        correctOne = 2
    ),
    MyQuestion(//Question 15
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