package cat.itb.m78.exercices.exercicis01_Stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.generatedFace
import org.jetbrains.compose.resources.painterResource

data class Contact(val fullName: String, val email: String, val phone: String)
val contact = Contact("Marta Casserres", "marta@example.com", "934578484")

@Composable
fun Contact(){
    Column (horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(Res.drawable.generatedFace),
            modifier = Modifier.size(150.dp).padding(20.dp).clip(CircleShape),
            contentDescription = null
        )
        Text(contact.fullName, fontSize = 2.em, fontWeight = FontWeight.Bold)
        Card{
            Column {
                Row{
                    Icon(Icons.Default.Email, "email")
                    Text(contact.email)
                }
                Row{
                    Icon(Icons.Default.Phone, "phone")
                    Text(contact.phone)
                }
            }
        }
    }
}