package cat.itb.m78.exercices.Examen_M78P2

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.itb.m78.exercices.db.Students

@Composable
fun StudensMissings(
    students: List<Student>?,
    missings: List<Students>?
){
    if (students != null)
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        for (student in students){
            item {
                Text(" --- ${student.name} --- ",
                    fontWeight = FontWeight(500),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
                if (missings != null) {
                    LazyColumn {
                        val studentMissings = missings.filter { it.studentId.toInt() == student.id }
                        for (sMissing in studentMissings) {
                            item {
                                Text(" - ${sMissing.dateMissing}", textAlign = TextAlign.Center)
                            }
                        }
                    }
                } else{
                    Spacer(modifier = Modifier.size(15.dp))
                    Text("No te cap falta registrada")
                    Spacer(modifier = Modifier.size(15.dp))
                }
                Text(" ------------------------------------------ ",
                    fontWeight = FontWeight(500),
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(75.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.size(75.dp))
        }
    }
}