package cat.itb.m78.exercices.Navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cat.itb.m78.exercices.State.GoodTime
import cat.itb.m78.exercices.State.SayHello
import cat.itb.m78.exercices.State.diceRoller
import cat.itb.m78.exercices.State.secretNum
import cat.itb.m78.exercices.Stateless.ContactApp
import cat.itb.m78.exercices.Stateless.Resource
import cat.itb.m78.exercices.Stateless.Welcome
import cat.itb.m78.exercices.Stateless.helloWorld
import cat.itb.m78.exercices.ViewModel.CounterWithViewModel

@Composable
fun ExercisesLibraryScreen(){
    Button(onClick = {

    }){
        Text("")
    }

    Button(onClick = {

    }){
        Text("")
    }

    Button(onClick = {

    }){
        Text("")
    }

    Button(onClick = {

    }){
        Text("")
    }
}

//Stateless Exercises Screens
@Composable
fun StatelessScreen(){
    Column {
        Button(onClick = {

        }){
            Text("HelloWorld")
        }

        Button(onClick = {

        }){
            Text("Welcome")
        }

        Button(onClick = {

        }){
            Text("Resource")
        }

        Button(onClick = {

        }){
            Text("Contact")
        }

        Button(onClick = {

        }){
            Text("MessagesList")
        }
    }
}

@Composable
fun HellWorldScreen(){
    helloWorld()
}
@Composable
fun WelcomeScreen(){
    Welcome()
}
@Composable
fun ResourceScreen(){
    Resource()
}
@Composable
fun ContactScreen(){
    ContactApp()
}
@Composable
fun MessagesListScreen(){

}

//State Exercises Screens
@Composable
fun StateScreen(){
    Column {
        Button(onClick = {

        }){
            Text("GoodMorningAndNight")
        }

        Button(onClick = {

        }){
            Text("SayHelloScreen")
        }

        Button(onClick = {

        }){
            Text("SecretNumber")
        }

        Button(onClick = {

        }){
            Text("DiceRoller")
        }
    }
}
@Composable
fun GoodMorningAndNight(){
    GoodTime()
}
@Composable
fun SayHelloScreen(){
    SayHello()
}
@Composable
fun SecretNumberScreen(){
    secretNum()
}
@Composable
fun DiceRollerScreen(){
    diceRoller()
}

//View Model Exercises Screens
@Composable
fun ViewModelScreen(){
    Column {
        Button(onClick = {

        }){
            Text("Counter")
        }

        Button(onClick = {

        }){
            Text("ShoppingList")
        }
    }
}
@Composable
fun CounterScreen(){
    CounterWithViewModel()
}
@Composable
fun ShoppingListScreen(){

}

//Navigation Exercises Screens
@Composable
fun NavigationScreen(){
    Column {
        Button(onClick = {

        }){
            Text("ManualNav")
        }

        Button(onClick = {

        }){
            Text("LibraryNav")
        }
    }
}
@Composable
fun ManualNavScreen(){
    ManualNav()
}