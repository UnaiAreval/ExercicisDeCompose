package cat.itb.m78.exercices

import androidx.compose.runtime.*
import cat.itb.m78.exercices.calculatorApp.CalculatorNav

/* Exercicis
import cat.itb.m78.exercices.state.GoodTime
import cat.itb.m78.exercices.state.SayHello
import cat.itb.m78.exercices.state.DiceRoller
import cat.itb.m78.exercices.state.SecretNum
import cat.itb.m78.exercices.stateless.Contact
import cat.itb.m78.exercices.stateless.Resource
import cat.itb.m78.exercices.stateless.Welcome
import cat.itb.m78.exercices.viewModel.CounterWithViewModel
import cat.itb.m78.exercices.viewModel.CounterWhithoutViewModel
import cat.itb.m78.exercices.navigation.ManualNav
import cat.itb.m78.exercices.navigation.LibraryNavSample
 Final d'els exercicis*/
import cat.itb.m78.exercices.navigation.TicTacToeSample

//Llibreria amb tots els exercicis
import cat.itb.m78.exercices.navigation.NavExercisesScreenSample
//Practica del trivial
import cat.itb.m78.exercices.practicaTrivial.TrivialNavigateSample

import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    TicTacToeSample()
}