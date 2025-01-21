package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

data class Product (val name: String, val amount: Int)

@Composable
fun shoppingList(){
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = name,
            label = { Text(text = "Product") },
            onValueChange = { name = it }
        )
        TextField(
            value = amount,
            label = { Text(text = "Amount") },
            onValueChange = { amount = it }
        )
    }

}