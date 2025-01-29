package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.HelloWorld
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import kotlinx.serialization.Serializable

@Composable
fun NavExercisesScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Library) {
        composable<Destination.Library> {
            LibraryScreen(
                navigateToStatelessScreen = { navController.navigate(Destination.Stateless) },
                navigateToStateScreen = { navController.navigate(Destination.State) },
                navigateToViewModelScreen = { navController.navigate(Destination.ViewModel) },
                navigateToNavigationScreen = { navController.navigate(Destination.Navigation) }
            )
        }
        composable<Destination.Stateless> {
            StatelessScreen(
                navigateToHelloWorld = { navController.navigate(Destination.HelloWorld) },
                navigateToWelcome = { navController.navigate(Destination.Welcome) },
                navigateToResource = { navController.navigate(Destination.Resource) },
                navigateToContact = { navController.navigate(Destination.Contact) },
                navigateToMessagesList = { navController.navigate(Destination.MessagesList) }
            )
        }
        composable<Destination.HelloWorld> { HelloWorld() }
        composable<Destination.Welcome> { Welcome() }
        composable<Destination.Resource> { Resource() }
        composable<Destination.Contact> { Contact() }
        composable<Destination.MessagesList> {  }
    }
}

object Destination {
    @Serializable
    data object Library
    // Stateless Exercises
    @Serializable
    data object Stateless
    @Serializable
    data object HelloWorld
    @Serializable
    data object Welcome
    @Serializable
    data object Resource
    @Serializable
    data object Contact
    @Serializable
    data object MessagesList

    // State Exercises
    @Serializable
    data object State
    @Serializable
    data object ViewModel
    @Serializable
    data object Navigation
}

@Composable
fun LibraryScreen(navigateToStatelessScreen:()-> Unit,
                           navigateToStateScreen:()-> Unit,
                           navigateToViewModelScreen:()-> Unit,
                           navigateToNavigationScreen:()-> Unit){

    Column {
        Button(onClick = { navigateToStatelessScreen() }) {
            Text("Stateless Exercises")
        }

        Button(onClick = { navigateToStateScreen() }) {
            Text("State Exercises")
        }

        Button(onClick = { navigateToViewModelScreen() }) {
            Text("ViewModel Exercises")
        }

        Button(onClick = { navigateToNavigationScreen() }) {
            Text("Navigation Exercises")
        }
    }

}

//Stateless Exercises Screens
@Composable
fun StatelessScreen(navigateToHelloWorld:()-> Unit,
                    navigateToWelcome:()-> Unit,
                    navigateToResource:()-> Unit,
                    navigateToContact:()-> Unit,
                    navigateToMessagesList:()-> Unit){

    Column {
        Button(onClick = { navigateToHelloWorld() }){
            Text("HelloWorld")
        }

        Button(onClick = { navigateToWelcome() }){
            Text("Welcome")
        }

        Button(onClick = { navigateToResource() }){
            Text("Resource")
        }

        Button(onClick = { navigateToContact() }){
            Text("Contact")
        }

        Button(onClick = { navigateToMessagesList() }){
            Text("MessagesList")
        }
    }
}

//State Exercises Screens
@Composable
fun StateScreen(){
    Column {
        Button(onClick = {

        }){
            Text("GoodMorningAndNight")
        }

        Button(onClick = {

        }){
            Text("SayHelloScreen")
        }

        Button(onClick = {

        }){
            Text("SecretNumber")
        }

        Button(onClick = {

        }){
            Text("DiceRoller")
        }
    }
}

//View Model Exercises Screens
@Composable
fun ViewModelScreen(){
    Column {
        Button(onClick = {

        }){
            Text("Counter")
        }

        Button(onClick = {

        }){
            Text("ShoppingList")
        }
    }
}

//Navigation Exercises Screens
@Composable
fun NavigationScreen(){
    Column {
        Button(onClick = {

        }){
            Text("ManualNav")
        }

        Button(onClick = {

        }){
            Text("LibraryNav")
        }
    }
}