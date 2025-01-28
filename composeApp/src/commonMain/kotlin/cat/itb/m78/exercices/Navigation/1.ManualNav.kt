package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.TestModifierUpdaterLayout

@Composable
fun MainManualNav(navigateToScreen3: (String)-> Unit){
    Column {
        Button(onClick = {

        }){ Text("Screen 1") }
        Button(onClick = {

        }){ Text("Screen 2") }
        Button(onClick = {
            navigateToScreen3("Hello")
        }){ Text("Screen 3 - Hello") }
        Button(onClick = {
            navigateToScreen3("Bye")
        }){ Text("Screen 3 - Bye") }
    }
}
@Composable
fun Screen1(navigateToMainManualNav: ()-> Unit){
    Column {
        Text("Screen 1")
        Button(onClick = navigateToMainManualNav){
            Text("Back to main menu")
        }
    }
}
@Composable
fun Screen2(navigateToMainManualNav: ()-> Unit){
    Column {
        Text("Screen 2")
        Button(onClick = navigateToMainManualNav){
            Text("Back to main menu")
        }
    }
}
@Composable
fun Screen3(message: String, navigateToMainManualNav: ()-> Unit){
    Column {
        Text("Screen 3")
        Text(message)
        Button(onClick = navigateToMainManualNav){
            Text("Back to main menu")
        }
    }
}