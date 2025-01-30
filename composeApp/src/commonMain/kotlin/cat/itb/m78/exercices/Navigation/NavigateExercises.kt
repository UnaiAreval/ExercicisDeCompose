package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.State.DiceRoller
import cat.itb.m78.exercices.State.GoodTime
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.State.SecretNum
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.HelloWorld
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.ViewModel.CounterWhithoutViewModel
import cat.itb.m78.exercices.ViewModel.CounterWithViewModel
import kotlinx.serialization.Serializable

@Composable
fun NavExercisesScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ExerciseDestination.Library) {
        composable<ExerciseDestination.Library> {
            LibraryScreen(
                navigateToStatelessScreen = { navController.navigate(ExerciseDestination.Stateless) },
                navigateToStateScreen = { navController.navigate(ExerciseDestination.State) },
                navigateToViewModelScreen = { navController.navigate(ExerciseDestination.ViewModel) },
                navigateToNavigationScreen = { navController.navigate(ExerciseDestination.Navigation) }
            )
        }
        composable<ExerciseDestination.NonFinished> {
            NonFinished(
                navigateToMainMenu = { navController.navigate(ExerciseDestination.Library) }
            )
        }
        composable<ExerciseDestination.Stateless> {
            StatelessScreen(
                navigateToHelloWorld = { navController.navigate(ExerciseDestination.HelloWorld) },
                navigateToWelcome = { navController.navigate(ExerciseDestination.Welcome) },
                navigateToResource = { navController.navigate(ExerciseDestination.Resource) },
                navigateToContact = { navController.navigate(ExerciseDestination.Contact) },
                navigateToMessagesList = { navController.navigate(ExerciseDestination.MessagesList) }
            )
        }
        composable<ExerciseDestination.HelloWorld> { HelloWorld() }
        composable<ExerciseDestination.Welcome> { Welcome() }
        composable<ExerciseDestination.Resource> { Resource() }
        composable<ExerciseDestination.Contact> { Contact() }
        composable<ExerciseDestination.MessagesList> { NonFinished( navigateToMainMenu = { navController.navigate(ExerciseDestination.Library) } ) }

        composable<ExerciseDestination.State> {
            StateScreen(
                navigateToGoodMorningNight = { navController.navigate(ExerciseDestination.GoodMorningNight) },
                navigateToSayHello = { navController.navigate(ExerciseDestination.SayHello) },
                navigateToSecretNumber = { navController.navigate(ExerciseDestination.SecretNum) },
                navigateToDiceRoller = { navController.navigate(ExerciseDestination.DiceRoller) }
            )
        }
        composable<ExerciseDestination.GoodMorningNight> { GoodTime() }
        composable<ExerciseDestination.SayHello> { SayHello() }
        composable<ExerciseDestination.SecretNum> { SecretNum() }
        composable<ExerciseDestination.DiceRoller> { DiceRoller() }

        composable<ExerciseDestination.ViewModel> {
            ViewModelScreen(
                navigateToCounter = { navController.navigate(ExerciseDestination.Counter) },
                navigateToCounterNoViewModel = { navController.navigate(ExerciseDestination.CountNoViewModel) },
                navigateToShopingList = { navController.navigate(ExerciseDestination.ShopList) }
            )
        }
        composable<ExerciseDestination.Counter> { CounterWithViewModel() }
        composable<ExerciseDestination.CountNoViewModel> { CounterWhithoutViewModel() }
        composable<ExerciseDestination.ShopList> { NonFinished(navigateToMainMenu = { navController.navigate(ExerciseDestination.Library) }) }

        composable<ExerciseDestination.Navigation> {
            NavigationScreen(
                navigateToManualNav = { navController.navigate(ExerciseDestination.ManualNav) },
                navigateToLibraryNav = { navController.navigate(ExerciseDestination.LibraryNav) }
            )
        }
        composable<ExerciseDestination.ManualNav> { ManualNav() }
        composable<ExerciseDestination.LibraryNav> { LibraryNavSample() }
    }
}

object ExerciseDestination {
    @Serializable
    data object Library
    @Serializable
    data object NonFinished

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
    data object GoodMorningNight
    @Serializable
    data object SayHello
    @Serializable
    data object SecretNum
    @Serializable
    data object DiceRoller

    // ViewModel Exercises
    @Serializable
    data object ViewModel
    @Serializable
    data object Counter
    @Serializable
    data object CountNoViewModel
    @Serializable
    data object ShopList

    // Navigation Exercises
    @Serializable
    data object Navigation
    @Serializable
    data object ManualNav
    @Serializable
    data object LibraryNav
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
fun StateScreen(navigateToGoodMorningNight:()-> Unit,
                navigateToSayHello:()-> Unit,
                navigateToSecretNumber:()-> Unit,
                navigateToDiceRoller:()-> Unit){

    Column {
        Button(onClick = { navigateToGoodMorningNight() }){
            Text("GoodMorningAndNight")
        }

        Button(onClick = { navigateToSayHello() }){
            Text("SayHelloScreen")
        }

        Button(onClick = { navigateToSecretNumber() }){
            Text("SecretNumber")
        }

        Button(onClick = { navigateToDiceRoller() }){
            Text("DiceRoller")
        }
    }
}

//View Model Exercises Screens
@Composable
fun ViewModelScreen(navigateToCounter:()-> Unit,
                    navigateToCounterNoViewModel:()-> Unit,
                    navigateToShopingList:()-> Unit){

    Column {
        Button(onClick = { navigateToCounter() }){
            Text("Counter")
        }

        Button(onClick = { navigateToCounterNoViewModel() }){
            Text("Counter però mal fet")
        }

        Button(onClick = { navigateToShopingList() }){
            Text("ShoppingList")
        }
    }
}

//Navigation Exercises Screens
@Composable
fun NavigationScreen(navigateToManualNav:()-> Unit,
                     navigateToLibraryNav:()-> Unit){

    Column {
        Button(onClick = { navigateToManualNav() }){
            Text("ManualNav")
        }

        Button(onClick = { navigateToLibraryNav() }){
            Text("LibraryNav")
        }
    }
}

// Pendents
@Composable
fun NonFinished(navigateToMainMenu:()-> Unit){
    Column {
        Text("Aquest exercici no esta acabat")

        Button(onClick = { navigateToMainMenu() }){
            Text("Tornar al menu principal")
        }
    }
}