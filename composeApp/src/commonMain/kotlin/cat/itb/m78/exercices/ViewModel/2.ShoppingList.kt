package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.ViewModel

data class Product (val name: String, val amount: Int)
class ShopListViewModel : ViewModel(){
    val name = mutableStateOf("")
    val amount = mutableStateOf(0)
}


@Composable
fun shoppingList(){
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf(0) }
    val list = mutableListOf( listOf(Product(name, amount)) )

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = name,
            label = { Text(text = "Product") },
            onValueChange = { name = it }
        )
        TextField(
            value = amount.toString(),
            label = { Text(text = "Amount") },
            onValueChange = { amount = it.toInt() }
        )
        Button(onClick = {
        }){
            Text("Add Item")
        }
    }

}