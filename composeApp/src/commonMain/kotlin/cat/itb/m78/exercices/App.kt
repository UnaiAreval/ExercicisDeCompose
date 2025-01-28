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
import cat.itb.m78.exercices.ViewModel.CounterScreen
import cat.itb.m78.exercices.ViewModel.CounterWhithoutViewModel
import cat.itb.m78.exercices.ViewModel.shoppingList
import cat.itb.m78.exercices.Navigation.ManualNav
// Final d'els exercicis

import cat.itb.m78.exercices.theme.AppTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
internal fun App() = AppTheme {
    ManualNav()
}