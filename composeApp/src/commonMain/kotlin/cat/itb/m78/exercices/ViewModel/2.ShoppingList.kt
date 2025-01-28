package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

data class Product(val name: String, val amount: Int)

class ShopingListViewModel(name: String, amount: String) : ViewModel(){
    val name = mutableStateOf("")
    val amount = mutableStateOf("")
    val proucts = mutableStateOf(listOf<Product>())

    fun changeName(newName: String){
        name.value = newName
    }
    fun changeAmount(newAmount: String){
        amount.value = newAmount
    }
    fun addProduct(){
        if (amount.value.toIntOrNull()==null) return
        val newProduct = Product(name.value, amount.value.toInt())
        val newList = proucts.value+newProduct
        proucts.value = newList
        name.value = ""
        amount.value = ""
    }
}

@Composable
fun ShopListScreen(){

}

@Composable
fun shoppingList(
    name: String,
    amount: String,
    product: Product,
    onNameChanged: (String)->Unit,
    onAmountChanged: (String)->Unit
){
    Column {
        Card{
            Column {
                OutlinedTextField(name, onNameChanged,
                    label = {Text("Name")})

            }
        }
    }
}