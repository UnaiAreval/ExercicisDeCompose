package cat.itb.m78.exercices

import androidx.compose.runtime.*

// Exercicis
import cat.itb.m78.exercices.State.GoodTime
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.State.diceRoller
import cat.itb.m78.exercices.State.secretNum
import cat.itb.m78.exercices.Stateless.Contact
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.ViewModel.CounterWithViewModel
import cat.itb.m78.exercices.ViewModel.CounterWhithoutViewModel
import cat.itb.m78.exercices.Navigation.ManualNav
// Final d'els exercicis

//Llibreria amb tots els exercicis
import cat.itb.m78.exercices.Navigation.NavExercisesScreenSample

import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    NavExercisesScreenSample()
}