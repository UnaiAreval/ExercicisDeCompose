package cat.itb.m78.exercices.Examen_M78P2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.house
import org.jetbrains.compose.resources.painterResource

object StudentsNavigation{
    @Serializable
    data object StudentsList
    @Serializable
    data object MissingsList
    @Serializable
    data object StudentsWithMissings
}

@Composable
fun StudentsScreen(){
    val viewModelStudentsList = viewModel{ StudentsListViewModel() }
    val viewModelMissings = viewModel{ StudentsMissingsViewModel() }
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomAppBar (
            actions = {
                NavigationBarItem(
                    onClick = {
                        navController.navigate(StudentsNavigation.StudentsList)
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.house),
                            contentDescription = "SList",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text("Llista estudiants")
                    }
                )
                NavigationBarItem(
                    onClick = {
                        navController.navigate(StudentsNavigation.MissingsList)
                        viewModelMissings.getMissings()
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.house),
                            contentDescription = "MissingList",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text("Faltes")
                    }
                )
                NavigationBarItem(
                    onClick = {
                        navController.navigate(StudentsNavigation.StudentsWithMissings)
                        viewModelMissings.getMissings()
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.house),
                            contentDescription = "SListWithMissings",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = {
                        Text("Estudiants amb nombre de faltes")
                    }
                )
            }, modifier = Modifier.background(color = Color.Green)
        )
    }) {
        NavHost(modifier = Modifier.background(color = Color.Cyan),
            navController = navController, startDestination = StudentsNavigation.StudentsList){
            composable<StudentsNavigation.StudentsList> {
                StudentsList(
                    viewModelStudentsList.students.value,
                    addMissingToStudent = { viewModelStudentsList.addMissing(it) }
                )
            }
            composable<StudentsNavigation.MissingsList> {
                StudensMissings(
                    viewModelMissings.students.value,
                    viewModelMissings.studentsMissings.value
                )
            }
            composable<StudentsNavigation.StudentsWithMissings> {
                StudentsWithMissingsAmount(
                    viewModelMissings.students.value,
                    viewModelMissings.studentsMissings.value
                )
            }
        }
    }
}