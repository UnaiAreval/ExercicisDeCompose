package cat.itb.m78.exercices.practicaTrivial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import kotlin.random.Random

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
        unit = "Science",
        askFor = "What temperature does water have to be to boil at sea level?",
        answer1 = "50ºC",
        answer2 = "100ºC",
        answer3 = "120ºC",
        answer4 = "39ºC",
        clueItsYourAnswerCorrect = "Water is the best drink, I'm '100'% sure.",
        correctOne = 2
    ),
    MyQuestion(//Question 2
        unit = "Garfield",
        askFor =  "Which day of the week does Garfield hate?",
        answer1 = "Wednesday",
        answer2 = "Juernes",
        answer3 = "Monday",
        answer4 = "Sunday",
        clueItsYourAnswerCorrect = "He hates the first day of work",
        correctOne = 3
    ),
    MyQuestion(//Question 3
        unit = "Hello Kitty",
        askFor = "What does Hello Kittys name mean?",
        answer1 = "Meaw meaw",
        answer2 = "Hello demon",
        answer3 = "Hola gatito",
        answer4 = "Puss in boots",
        clueItsYourAnswerCorrect = "You just need to translate it.",
        correctOne = 3
    ),
    MyQuestion(//Question 4
        unit = "Light",
        askFor = "Which are the primary colors of light?",
        answer1 = "Magenta, Green and Cyan",
        answer2 = "Red, Green and Blue",
        answer3 = "Magenta, Yellow and Cyan",
        answer4 = "Red, Yellow and Blue",
        clueItsYourAnswerCorrect = "Yellow and Green change places depending if you ask for \n the primary light colors and the primary pigment colors.",
        correctOne = 2
    ),
    MyQuestion(//Question 5
        unit = "Maths",
        askFor =  "How much is 33 + 77",
        answer1 = "100",
        answer2 = "107",
        answer3 = "110",
        answer4 = "103",
        clueItsYourAnswerCorrect = "30 + 70 = 100 \n 3 + 7 = 10 \n (30 + 70) + (3 + 7) = ?",
        correctOne = 3
    ),
    MyQuestion(//Question 6
        unit = "JR Tolkien",
        askFor =  "How is known Thorin?",
        answer1 = "Thorin Oakenshield",
        answer2 = "Thorin the gray",
        answer3 = "Thorin, the ring bearer",
        answer4 = "Thorin Baggins",
        clueItsYourAnswerCorrect = "He fought an ork using an oak trunk as a shield.",
        correctOne = 1
    ),
    MyQuestion(//Question 7
        unit = "Gambling",
        askFor =  "Chose the correct color and win two points",
        answer1 = "Red",
        answer2 = "Blue",
        answer3 = "Yellow",
        answer4 = "Green",
        clueItsYourAnswerCorrect = "",
        correctOne = 0
    ),
    MyQuestion(//Question 8
        unit = "Mythology",
        askFor =  "How will you call a dragon with two bat wings and two legs?",
        answer1 = "Chines dragon",
        answer2 = "Tarasca",
        answer3 = "Common dragon",
        answer4 = "Wyvern",
        clueItsYourAnswerCorrect = "Chines dragons doesn't have wings, \n the Tarasca it's also named like Cucafera \n and common dragons have fore legs.",
        correctOne = 4
    ),
    MyQuestion(//Question 9
        unit = "Science-Biology",
        askFor = "Which of this animals is not an arachnid?",
        answer1 = "Scorpion",
        answer2 = "Megarachne",
        answer3 = "Tick",
        answer4 = "All of them are arachnids",
        clueItsYourAnswerCorrect = "Sometimes names are deceiving",
        correctOne = 2
    ),
    MyQuestion(//Question 10
        unit = "WarHammer 40.000",
        askFor = "Which is the characteristic of a purple ork?",
        answer1 = "They are faster",
        answer2 = "They are lucky",
        answer3 = "They explode better",
        answer4 = "They are invisible",
        clueItsYourAnswerCorrect = "Have you ever seen a purple ork?",
        correctOne = 4
    ),
    MyQuestion(//Question 11
        unit = "Mythology",
        askFor =  "Which is between this gods and goddesses who is not a moon deity?",
        answer1 = "Selene",
        answer2 = "Thoth",
        answer3 = "Ilargi",
        answer4 = "Eguzkilore",
        clueItsYourAnswerCorrect = "Eguziklore was as shining as the sun",
        correctOne = 4
    ),
    MyQuestion(//Question 12
        unit = "Computer games",
        askFor =  "What year did the PS2 come out?",
        answer1 = "2000",
        answer2 = "2004",
        answer3 = "2006",
        answer4 = "2013",
        clueItsYourAnswerCorrect = "The PS2 came out with the new millennium",
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
        unit = "Gambling",
        askFor =  "Chose the correct color and win two points",
        answer1 = "Red",
        answer2 = "Blue",
        answer3 = "Yellow",
        answer4 = "Green",
        clueItsYourAnswerCorrect = "",
        correctOne = 0
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

@Composable
fun QuestionUsingList(
    navigateToResultScreen:(Int) -> Unit
){
    val questionAnswered = remember{ mutableStateOf(false) }
    val answeredWell = remember{ mutableStateOf(false) }
    val question = remember{ mutableStateOf(0) }
    val unit = questions[question.value].unit
    val askFor =  questions[question.value].askFor
    val answer1 = questions[question.value].answer1
    val answer2 = questions[question.value].answer2
    val answer3 = questions[question.value].answer3
    val answer4 = questions[question.value].answer4
    val clueItsYourAnswerCorrect = questions[question.value].clueItsYourAnswerCorrect
    val correctOne = questions[question.value].correctOne
    val roundsCounter = remember{ mutableStateOf(1) }
    val points = remember { mutableStateOf(0) }

    //Only for questions 7 and 14, which are a gambling question
    val correctColor = remember{ mutableStateOf( Random.nextInt(1, 5) ) }

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        if (!questionAnswered.value){
            Text(unit, modifier = Modifier.padding(20.dp))
            Spacer(modifier = Modifier.size(25.dp))

            Image(
                painter = painterResource(Res.drawable.trivial),
                modifier = Modifier.size(150.dp).padding(15.dp),
                contentDescription = null
            )

            Text(askFor)
            Spacer(modifier = Modifier.size(10.dp))
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = {
                            if (!questionAnswered.value && correctOne != 0) {
                                if (correctOne == 1) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            } else if (!questionAnswered.value){
                                if (correctColor.value == 1) {
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
                            if (!questionAnswered.value && correctOne != 0) {
                                if (correctOne == 3) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            } else if (!questionAnswered.value){
                                if (correctColor.value == 3) {
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
                            if (!questionAnswered.value && correctOne != 0) {
                                if (correctOne == 2) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            } else if (!questionAnswered.value){
                                if (correctColor.value == 2) {
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
                            if (!questionAnswered.value && correctOne != 0){
                                if (correctOne == 4) {
                                    answeredWell.value = true
                                }
                                questionAnswered.value = true
                            } else if (!questionAnswered.value){
                                if (correctColor.value == 4) {
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
            if (correctOne != 0){
                Text(clueItsYourAnswerCorrect, textAlign = TextAlign.Center)
            } else{
                if (correctColor.value == 1){ Text("The color Red has come out") }
                else if (correctColor.value == 2){ Text("The color Blue has come out") }
                else if (correctColor.value == 3){ Text("The color Yellow has come out") }
                else{ Text("The color Green has come out") }
            }
            Button(onClick = {
                if (roundsCounter.value == rounds){
                    navigateToResultScreen(points.value)
                } else{
                    if (answeredWell.value){
                        if (correctOne == 0){
                            points.value += 2
                            correctColor.value = Random.nextInt(1, 5)
                        } else {
                            points.value ++
                        }
                        question.value++
                        roundsCounter.value++
                        answeredWell.value = false
                        questionAnswered.value = false
                    } else {
                        question.value++
                        roundsCounter.value++
                        questionAnswered.value = false
                    }
                }
            }){
                if (roundsCounter.value == rounds){
                    Text("Go back to menu")
                } else{
                    Text("Next Question")
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        if (answeredWell.value){
            if (correctOne == 0){
                Text("Your points ${points.value + 2}", textAlign = TextAlign.Center)
            } else {
                Text("Your points ${points.value + 1}", textAlign = TextAlign.Center)
            }
        } else {
            Text("Your points ${points.value}", textAlign = TextAlign.Center)
        }
    }
}