package cat.itb.m78.exercices



import androidx.compose.runtime.*
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Contact()
}
