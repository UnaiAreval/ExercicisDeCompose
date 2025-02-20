package cat.itb.m78.exercices.exercicis03_ViewModel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.corona
import m78exercices.composeapp.generated.resources.papelera
import m78exercices.composeapp.generated.resources.puntosSuspensivos
import org.jetbrains.compose.resources.painterResource

@Composable
fun CounterWhithoutViewModel(){

    val scoreOne = remember { mutableStateOf(0) }
    val scoreTwo = remember { mutableStateOf(0) }

    Row (modifier = Modifier.fillMaxSize()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Column ( horizontalAlignment = Alignment.CenterHorizontally ) {
                if (scoreTwo.value > scoreOne.value){
                    Image(
                        painter = painterResource(Res.drawable.papelera),
                        modifier = Modifier.size(100.dp),
                        contentDescription = null
                    )
                } else {
                    if (scoreTwo.value < scoreOne.value){
                        Image(
                            painter = painterResource(Res.drawable.corona),
                            modifier = Modifier.size(100.dp),
                            contentDescription = null
                        )
                    } else{
                        Image(
                            painter = painterResource(Res.drawable.puntosSuspensivos),
                            modifier = Modifier.size(100.dp),
                            contentDescription = null
                        )
                    }
                }

                Text(
                    "" + scoreOne.value,
                    fontSize = 2.em,
                    modifier = Modifier.background(color = Color.LightGray).padding(10.dp)
                )
            }

            Row {
                Button(onClick = {
                    scoreOne.value++
                }) {
                    Text("Score Up")
                }
                Button(onClick = {
                    if (scoreOne.value > 0){
                        scoreOne.value--
                    }
                }) {
                    Text("Score Down")
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Column ( horizontalAlignment = Alignment.CenterHorizontally ) {
                if (scoreTwo.value < scoreOne.value) {
                    Image(
                        painter = painterResource(Res.drawable.papelera),
                        modifier = Modifier.size(100.dp),
                        contentDescription = null
                    )
                } else {
                    if (scoreTwo.value > scoreOne.value){
                        Image(
                            painter = painterResource(Res.drawable.corona),
                            modifier = Modifier.size(100.dp),
                            contentDescription = null
                        )
                    } else{
                        Image(
                            painter = painterResource(Res.drawable.puntosSuspensivos),
                            modifier = Modifier.size(100.dp),
                            contentDescription = null
                        )
                    }
                }

                Text(
                    "" + scoreTwo.value,
                    fontSize = 2.em,
                    modifier = Modifier.background(color = Color.LightGray).padding(10.dp)
                )
            }

            Row {
                Button(onClick = {
                    scoreTwo.value++
                }) {
                    Text("Score Up")
                }
                Button(onClick = {
                    if (scoreTwo.value > 0) {
                        scoreTwo.value--
                    }
                }) {
                    Text("Score Down")
                }
            }
        }
    }
}

class CounterViewModel : ViewModel(){
    val counter1 = mutableStateOf(0)
    val counter2 = mutableStateOf(0)

    fun increaseCounter1(){
        counter1.value++
    }

    fun increaseCounter2(){
        counter2.value++
    }
}
@Composable
fun CounterWithViewModel(){
    val viewModel = viewModel{ CounterViewModel() }

    Row {
        Column {
            Text( viewModel.counter1.value.toString() )
            Button(onClick = {
                viewModel.increaseCounter1()
            }){
                Text("Score Up")
            }
        }

        Column {
            Text( viewModel.counter2.value.toString() )
            Button(onClick = { viewModel.increaseCounter2() }){
                Text("Score Up")
            }
        }
    }
}