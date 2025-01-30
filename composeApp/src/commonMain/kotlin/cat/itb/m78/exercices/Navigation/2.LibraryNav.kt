package cat.itb.m78.exercices.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object Destination{
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
    NavHost(navController = navController, startDestination = Destination.MainScreen){
        composable<Destination.MainScreen> {
            MainScreen(
                navigateToScreen1 = { navController.navigate(Destination.FirstScreen) },
                navigateToScreen2 = { navController.navigate(Destination.SecondScreen) },
                navigateToScreen3 = { navController.navigate(Destination.ThirdScreen(it)) }
            )
        }
        composable<Destination.FirstScreen> {
            Screen1 (
                navigateToMainManualNav = { navController.navigate(Destination.MainScreen) }
            )
        }
        composable<Destination.SecondScreen> {
            Screen2 (
                navigateToMainManualNav = { navController.navigate(Destination.MainScreen) }
            )
        }
        composable<Destination.ThirdScreen> { backStack ->
            val message = backStack.toRoute<Destination.ThirdScreen>().message
            Screen3(message,
                navigateToMainManualNav = { navController.navigate(Destination.MainScreen) })
        }
    }
}