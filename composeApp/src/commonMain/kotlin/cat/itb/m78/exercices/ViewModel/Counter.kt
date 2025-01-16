package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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

@Composable
fun Counter(){
    val scoreOne = remember { mutableStateOf(0) }
    val scoreTwo = remember { mutableStateOf(0) }

    Row {
        Row {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "" + scoreOne.value,
                    fontSize = 2.em,
                    modifier = Modifier.background(color = Color.LightGray).padding(10.dp)
                )

                Row {
                    Button(onClick = {
                        scoreOne.value++
                    }) {
                        Text("Up")
                    }
                    Button(onClick = {
                        if (scoreOne.value > 0){
                            scoreOne.value--
                        }
                    }) {
                        Text("Down")
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    "" + scoreTwo.value,
                    fontSize = 2.em,
                    modifier = Modifier.background(color = Color.LightGray).padding(10.dp)
                )

                Row {
                    Button(onClick = {
                        scoreTwo.value++
                    }) {
                        Text("Up")
                    }
                    Button(onClick = {
                        if (scoreTwo.value > 0) {
                            scoreTwo.value--
                        }
                    }) {
                        Text("Down")
                    }
                }
            }
        }
    }
}