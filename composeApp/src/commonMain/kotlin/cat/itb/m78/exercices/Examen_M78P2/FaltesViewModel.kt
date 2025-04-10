package cat.itb.m78.exercices.Examen_M78P2

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.db.Students
import cat.itb.m78.exercices.db.database
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class StudentsListViewModel : ViewModel(){
    val students = mutableStateOf<List<Student>?>(null)

    init {
        viewModelScope.launch {
            students.value = StudentsApi.list()
        }
    }

    fun addMissing(studentId: Int){
        //database.studentsTableQueries.insert(studentId.toLong())
    }
}

class StudentsMissingsViewModel : ViewModel(){
    val students = mutableStateOf<List<Student>?>(null)
    val studentsMissings = mutableStateOf<List<Students>?>(null)

    init {
        viewModelScope.launch {
            students.value = StudentsApi.list()
        }
    }

    fun getMissings() {
        //studentsMissings.value = database.studentsTableQueries.selectAll().executeAsList()
    }
}

object StudentsApi{
    private val url = "https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json"
    private val client = HttpClient(){
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(url).body<List<Student>>()
}

@Serializable
data class Student(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("surnames") val surnames: String,
    @SerialName("email") val email: String,
    @SerialName("photo_link") val photoURL: String
)