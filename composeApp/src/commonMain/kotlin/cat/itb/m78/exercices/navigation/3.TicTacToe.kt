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
        composable<TTTDestination.Winner> {
            WinnerScreen(
                navigateToPlay = { navController.navigate(TTTDestination.Play) }
            )
        }
    }
}

@Composable
fun List<List<Boolean?>>.toMutableMatrix(): List<MutableList<Boolean?>> {
    return map { it.toMutableList() }
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
fun PlayScreen(navigateToWinner:()-> Unit){
    val someoneWin = remember{ mutableStateOf(false) }
    //val boolList = List<List<Boolean?>>().toMutableMatrix()

    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {

        Column {
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
        }
        Column {
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
        }

        Column {
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
            Box{
                Button(onClick = {someoneWin.value = true}){
                    Text("Holiui")
                }
            }
        }
    }
    if (someoneWin.value){ navigateToWinner() }
}

@Composable
fun WinnerScreen(navigateToPlay:()-> Unit){

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text("El guanyador és ")
        Button(onClick = {navigateToPlay()}){
            Text("Play Again")
        }
    }
}