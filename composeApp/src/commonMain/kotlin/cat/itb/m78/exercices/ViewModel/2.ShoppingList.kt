package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class ShopListViewModel(value: String, value1: Int) : ViewModel(){
    val name = mutableStateOf("")
    val amount = mutableStateOf(0)

    fun funNewName(newName: String){
        name.value = newName
    }
    fun funNewAmount(newAmount: Int){
        amount.value = newAmount
    }
}


@Composable
fun shoppingList(){
    val viewModel = viewModel{ ShopListViewModel("", 0) }
    val shopList : MutableList<ShopListViewModel> = mutableListOf()

    Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = viewModel.name.value,
            label = { Text(text = "Product") },
            onValueChange = { viewModel.funNewName(it) }
        )
        TextField(
            value = viewModel.amount.value.toString(),
            label = { Text(text = "Amount") },
            onValueChange = { viewModel.funNewAmount(it.toInt()) }
        )
        Button(onClick = {
            shopList.add(ShopListViewModel(viewModel.name.value, viewModel.amount.value))

        }){
            Text("Add Item")
        }
        if (shopList.size > 0){
            for (i in 0..shopList.size){
                Text("Producte: ${shopList[i].name} Quantitat: ${shopList[i].amount}")
            }
        } else{
            Text("Llista buida")
        }
    }
}