package cat.itb.m78.exercices.Examen_CalculatorApp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

private sealed interface CalculatorScreen {
    data object Calculator : CalculatorScreen
    data class Result(val result: Int) : CalculatorScreen
}
private class CalculatorNavAppViewModel : ViewModel() {
    val currentScreen = mutableStateOf<CalculatorScreen>(CalculatorScreen.Calculator)
    fun navigateTo(screen: CalculatorScreen) {
        currentScreen.value = screen
    }
}

@Composable
fun CalculatorNav(){
    val viewModel = viewModel { CalculatorNavAppViewModel() }
    val currentScreen = viewModel.currentScreen.value
    when (currentScreen) {
        CalculatorScreen.Calculator -> Calculator(
            navigateToResultScreen = { viewModel.navigateTo(CalculatorScreen.Result(it))}
        )
        is CalculatorScreen.Result -> ResultScreen(
            currentScreen.result,
            navigateBackToCalculator = { viewModel.navigateTo(CalculatorScreen.Calculator) }
        )
    }
}