package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun Welcome(){
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Welcome!")
        Text("Start learning now")
        Button(onClick = {}){
            Text("Login")
        }
        Button(onClick = {}){
            Text("Register")
        }
    }
}