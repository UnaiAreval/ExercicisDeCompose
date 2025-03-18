package cat.itb.m78.exercices

import androidx.compose.runtime.*

// Exercicis
import cat.itb.m78.exercices.exercicis02_State.GoodTime
import cat.itb.m78.exercices.exercicis02_State.SayHello
import cat.itb.m78.exercices.exercicis02_State.DiceRoller
import cat.itb.m78.exercices.exercicis02_State.SecretNum
import cat.itb.m78.exercices.exercicis01_Stateless.Contact
import cat.itb.m78.exercices.exercicis01_Stateless.Resource
import cat.itb.m78.exercices.exercicis01_Stateless.Welcome
import cat.itb.m78.exercices.exercicis03_ViewModel.CounterWithViewModel
import cat.itb.m78.exercices.exercicis03_ViewModel.CounterWhithoutViewModel
import cat.itb.m78.exercices.exercicis04_Navigation.ManualNav
import cat.itb.m78.exercices.exercicis04_Navigation.LibraryNavSample
import cat.itb.m78.exercices.exercicis05_Settings.CountViewViewScreen
import cat.itb.m78.exercices.exercicis05_Settings.RememberNameViewScreen
import cat.itb.m78.exercices.exercicis06_Api.CountrieScreen
import cat.itb.m78.exercices.exercicis06_Api.EmbassamentScreen
import cat.itb.m78.exercices.exercicis06_Api.JokeScreen

//Llibreria amb tots els exercicis
//Practica del trivial
import cat.itb.m78.exercices.practicaTrivial.TrivialNavigateSample

import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    EmbassamentScreen()
}