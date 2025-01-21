package cat.itb.m78.exercices

import androidx.compose.runtime.*
// Exercicics
import cat.itb.m78.exercices.State.GoodTime
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.State.diceRoller
import cat.itb.m78.exercices.State.secretNum
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.ContactApp
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.ViewModel.Counter
//Final d'els exercicis
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    Counter()
}