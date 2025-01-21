package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun shoppingList(){
    var item by remember { mutableStateOf("") }
    var amount by remember{ mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(
            value = item,
            label = { Text(text = "Product") },
            onValueChange = { item = it }
        )

        TextField(
            value = amount,
            label = { Text(text = "Amount") },
            onValueChange = { amount = it }
        )

        Button(onClick = {

        }){
            Text("Add")
        }
    }
}