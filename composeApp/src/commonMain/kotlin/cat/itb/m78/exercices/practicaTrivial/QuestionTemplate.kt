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