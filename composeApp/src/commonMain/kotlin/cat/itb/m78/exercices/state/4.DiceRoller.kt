package cat.itb.m78.exercices.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dice_1
import m78exercices.composeapp.generated.resources.dice_2
import m78exercices.composeapp.generated.resources.dice_3
import m78exercices.composeapp.generated.resources.dice_4
import m78exercices.composeapp.generated.resources.dice_5
import m78exercices.composeapp.generated.resources.dice_6
import m78exercices.composeapp.generated.resources.dicerollericon
import m78exercices.composeapp.generated.resources.tapestry
import m78exercices.composeapp.generated.resources.title
import org.jetbrains.compose.resources.painterResource
import kotlin.random.Random

@Composable
fun DiceRoller(){

    val firstRandomNum = mutableStateOf(Random.nextInt(1, 7))
    val secondRandomNum = mutableStateOf(Random.nextInt(1, 7))

    // Imatge de fons
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(Res.drawable.tapestry),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }

    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.title),
            modifier = Modifier.size(150.dp).padding(20.dp),
            contentDescription = null
        )

        if (firstRandomNum.value == 6 && secondRandomNum.value == 6){
            Text("JACKPOT!", fontSize = 2.em, color = Color.Red)
        }

        Box(){
            //Daus
            Row {
                if (firstRandomNum.value == 1){
                    diceOne()
                } else{
                    if (firstRandomNum.value == 2){
                        diceTwo()
                    } else{
                        if (firstRandomNum.value == 3){
                            diceThree()
                        } else {
                            if (firstRandomNum.value == 4){
                                diceFour()
                            } else{
                                if (firstRandomNum.value == 5){
                                    diceFive()
                                } else{
                                    if (firstRandomNum.value == 6){
                                        diceSix()
                                    } else{
                                        rollingDice()
                                    }
                                }
                            }
                        }
                    }
                }

                if (secondRandomNum.value == 1){
                    diceOne()
                } else{
                    if (secondRandomNum.value == 2){
                        diceTwo()
                    } else{
                        if (secondRandomNum.value == 3){
                            diceThree()
                        } else {
                            if (secondRandomNum.value == 4){
                                diceFour()
                            } else{
                                if (secondRandomNum.value == 5){
                                    diceFive()
                                } else{
                                    if (secondRandomNum.value == 6){
                                        diceSix()
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }

        //Boto per llensar els daus
        Button(onClick = {
            firstRandomNum.value = Random.nextInt(1, 7)
        }){
            Text("ROLL THE DICE")
        }
    }
}
@Composable
fun diceOne(){
    Image(
        painter = painterResource(Res.drawable.dice_1),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}

@Composable
fun diceTwo(){
    Image(
        painter = painterResource(Res.drawable.dice_2),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}

@Composable
fun diceThree(){
    Image(
        painter = painterResource(Res.drawable.dice_3),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}

@Composable
fun diceFour(){
    Image(
        painter = painterResource(Res.drawable.dice_4),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}

@Composable
fun diceFive(){
    Image(
        painter = painterResource(Res.drawable.dice_5),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}

@Composable
fun diceSix(){
    Image(
        painter = painterResource(Res.drawable.dice_6),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}

@Composable
fun rollingDice(){
    Image(
        painter = painterResource(Res.drawable.dicerollericon),
        modifier = Modifier.size(300.dp).padding(20.dp),
        contentDescription = null
    )
}