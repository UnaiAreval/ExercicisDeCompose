package cat.itb.m78.exercices

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cat.itb.m78.exercices.State.GoodTime
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.State.diceRoller
import cat.itb.m78.exercices.State.secretNum
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.ContactApp
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.theme.AppTheme
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.tapestry
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun App() = AppTheme {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background image
        Image(
            painter = painterResource(Res.drawable.tapestry),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
    }
    diceRoller()
}