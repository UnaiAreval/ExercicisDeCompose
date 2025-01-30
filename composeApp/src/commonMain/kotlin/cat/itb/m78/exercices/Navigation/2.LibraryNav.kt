package cat.itb.m78.exercices.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object LibraryDestination{
    @Serializable
    data object MainScreen
    @Serializable
    data object FirstScreen
    @Serializable
    data object SecondScreen
    @Serializable
    data class ThirdScreen(val message: String)
}

@Composable
fun LibraryNavSample(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LibraryDestination.MainScreen){
        composable<LibraryDestination.MainScreen> {
            MainScreen(
                navigateToScreen1 = { navController.navigate(LibraryDestination.FirstScreen) },
                navigateToScreen2 = { navController.navigate(LibraryDestination.SecondScreen) },
                navigateToScreen3 = { navController.navigate(LibraryDestination.ThirdScreen(it)) }
            )
        }
        composable<LibraryDestination.FirstScreen> {
            Screen1 (
                navigateToMainManualNav = { navController.navigate(LibraryDestination.MainScreen) }
            )
        }
        composable<LibraryDestination.SecondScreen> {
            Screen2 (
                navigateToMainManualNav = { navController.navigate(LibraryDestination.MainScreen) }
            )
        }
        composable<LibraryDestination.ThirdScreen> { backStack ->
            val message = backStack.toRoute<LibraryDestination.ThirdScreen>().message
            Screen3(message,
                navigateToMainManualNav = { navController.navigate(LibraryDestination.MainScreen) })
        }
    }
}