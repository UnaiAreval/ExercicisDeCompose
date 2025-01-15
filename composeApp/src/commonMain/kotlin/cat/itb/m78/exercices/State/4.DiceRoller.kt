package cat.itb.m78.exercices.State

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dicerollericon
import m78exercices.composeapp.generated.resources.tapestry
import m78exercices.composeapp.generated.resources.title
import org.jetbrains.compose.resources.painterResource

@Composable
fun diceRoller(){
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(Res.drawable.title),
            modifier = Modifier.size(150.dp).padding(20.dp),
            contentDescription = null
        )
        Image(
            painter = painterResource(Res.drawable.dicerollericon),
            modifier = Modifier.size(300.dp).padding(20.dp),
            contentDescription = null
        )
        Button(onClick = {
            val nums = 1..6
            val firstRandomNum = {nums.random()}
            val secondRandomNum = {nums.random()}
        }){
            Text("ROLL THE DICE");
        }
    }
}