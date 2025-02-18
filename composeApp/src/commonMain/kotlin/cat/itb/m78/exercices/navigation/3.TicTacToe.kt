package cat.itb.m78.exercices.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object TTTDestination{
    @Serializable
    data object Start
    @Serializable
    data object Play
    @Serializable
    data class Winner(val winner: String)
}

@Composable
fun TicTacToeSample(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TTTDestination.Start){
        composable<TTTDestination.Start> {
            StartScreen(
                navigateToPlay = { navController.navigate(TTTDestination.Play) }
            )
        }
        composable<TTTDestination.Play> {
            PlayScreen(
                navigateToWinner = { navController.navigate(TTTDestination.Winner) }
            )
        }
        composable<TTTDestination.Winner> {backStack ->
            val winner = backStack.toRoute<TTTDestination.Winner>().winner
            WinnerScreen(
                winner,
                navigateToPlay = { navController.navigate(TTTDestination.Play) }
            )
        }
    }
}

@Composable
fun StartScreen(navigateToPlay: () -> Unit){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Button(onClick = {navigateToPlay()}){
            Text("Start")
        }
    }
}

@Composable
fun PlayScreen(navigateToWinner:(String)-> Unit){
    val someoneWin = remember{ mutableStateOf(false) }
    val player = remember { mutableStateOf(true) } //quan player == true estan jugant les X, i quan és false estan jugant les O
    val buttonsClicked = mutableListOf<List<Boolean>>(
        listOf(false, false, false),
        listOf(false, false, false),
        listOf(false, false, false)
    )
    val firstColumn = mutableListOf<Boolean?>(null, null, null)
    val secondColumn = mutableListOf<Boolean?>(null, null, null)
    val thirdColumn = mutableListOf<Boolean?>(null, null, null)
    var winner = ""

    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {

        Column {
            Button(onClick = {
                if (!buttonsClicked[1][1]){
                    firstColumn[1] = player.value
                }
            }){
                if (firstColumn[1] == true) Text("X")
                else if (firstColumn[1] == false) Text("O")
                else Text("")
            }
            Button(onClick = {
                if (!buttonsClicked[1][2]){
                    firstColumn[2] = player.value
                }
            }){
                if (firstColumn[2] == true) Text("X")
                else if (firstColumn[2] == false) Text("O")
                else Text("")
            }
            Button(onClick = {
                if (!buttonsClicked[1][3]){
                    firstColumn[3] = player.value
                }
            }){
                if (firstColumn[3] == true) Text("X")
                else if (firstColumn[3] == false) Text("O")
                else Text("")
            }
        }
        Column {
            Button(onClick = {
                if (!buttonsClicked[2][1]){
                    secondColumn[1] = player.value
                }
            }){
                if (secondColumn[1] == true) Text("X")
                else if (secondColumn[1] == false) Text("O")
                else Text("")
            }
            Button(onClick = {
                if (!buttonsClicked[2][2]){
                    secondColumn[2] = player.value
                }
            }){
                if (secondColumn[2] == true) Text("X")
                else if (secondColumn[2] == false) Text("O")
                else Text("")
            }
            Button(onClick = {
                if (!buttonsClicked[2][3]){
                    secondColumn[3] = player.value
                }
            }){
                if (secondColumn[3] == true) Text("X")
                else if (secondColumn[3] == false) Text("O")
                else Text("")
            }
        }

        Column {
            Button(onClick = {
                if (!buttonsClicked[3][1]){
                    thirdColumn[1] = player.value
                }
            }){
                if (thirdColumn[1] == true) Text("X")
                else if (thirdColumn[1] == false) Text("O")
                else Text("")
            }
            Button(onClick = {
                if (!buttonsClicked[3][2]){
                    thirdColumn[2] = player.value
                }
            }){
                if (thirdColumn[2] == true) Text("X")
                else if (thirdColumn[2] == false) Text("O")
                else Text("")
            }
            Button(onClick = {
                if (!buttonsClicked[3][3]){
                    thirdColumn[3] = player.value
                }
            }){
                if (thirdColumn[3] == true) Text("X")
                else if (thirdColumn[3] == false) Text("O")
                else Text("")
            }
        }
    }

    if (someoneWin.value){
        navigateToWinner(winner)
    }
}

@Composable
fun WinnerScreen(
    winner: String,
    navigateToPlay:()-> Unit
){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text("El guanyador és ")
        Button(onClick = {navigateToPlay()}){
            Text("Play Again")
        }
    }
}