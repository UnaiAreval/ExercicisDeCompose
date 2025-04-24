package cat.itb.m78.exercices.mapsApp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

data class Mark(
    val state: Int,
    val title: String
)

@Composable
fun MarkerListScreen(
    marksTitles: List<String>
) {
    LazyColumn {
        for (title in marksTitles){
            item {
                Text(title)
            }
        }
    }
}