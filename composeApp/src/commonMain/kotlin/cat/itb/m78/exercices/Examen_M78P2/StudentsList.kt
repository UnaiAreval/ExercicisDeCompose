package cat.itb.m78.exercices.Examen_M78P2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun StudentsList(
    students: List<Student>?,
    addMissingToStudent: (Int) -> Unit
){
    if (students != null) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            for (student in students) {
                item {
                    Row {
                        Column (horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("------------------------------------------")
                            Text(
                                student.name,
                                fontWeight = FontWeight(500),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                student.email,
                                fontWeight = FontWeight(400),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                            AsyncImage(
                                modifier = Modifier.size(100.dp),
                                model = student.photoURL,
                                contentDescription = "student image",
                            )
                            Text("------------------------------------------")
                            Spacer(modifier = Modifier.size(75.dp))
                        }
                        Button(onClick = {
                            addMissingToStudent(student.id)
                        }, modifier = Modifier.padding(100.dp)) {
                            Text("Add missing")
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.size(75.dp))
            }
        }
    }
}