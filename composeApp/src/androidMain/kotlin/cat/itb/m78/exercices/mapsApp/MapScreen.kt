package cat.itb.m78.exercices.mapsApp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

object MapNavigation{
    @Serializable
    data object PrincipalScreen
    @Serializable
    data class AddMark(val cords: List<Double>)
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapAppScreen(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val mapViewModel = viewModel{ MapViewModel() }
    val cords = remember { mutableStateOf( LatLng(0.00, 0.00) ) }
    val cameraPositionState = rememberCameraPositionState() {
        position = CameraPosition.fromLatLngZoom(cords.value, 5f)
    }
    val displayMarksList = remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Mapa de grafitis", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text(text = "Mapa i marcadors") },
                    selected = false,
                    onClick = {
                        displayMarksList.value = false
                    }
                )
                NavigationDrawerItem(
                    label = { Text(text = "Llista de marcadors") },
                    selected = false,
                    onClick = {
                        displayMarksList.value = true
                    }
                )
            }
        }, gesturesEnabled = false, drawerState = drawerState
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = MapNavigation.PrincipalScreen) {
            composable<MapNavigation.PrincipalScreen> {
                if (displayMarksList.value){
                    MarkerListScreen(
                        marks = mapViewModel.marks.value!!
                    )
                }
                else{
                    MapScreen(
                        marks = mapViewModel.marks.value!!,
                        cameraPositionState,
                        cords.value,
                        navigateToMarkAdition = { navController.navigate(MapNavigation.AddMark(it)) }
                    )
                }
            }
        }
    }
}

data class Mark(
    val latitude: String,
    val longitude: String,
    val title: String,
    val imageLoc: String,
    val description: String
)

class MapViewModel : ViewModel(){
    val marks: MutableState<List<Mark>?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            marks.value = listOf(
                Mark("-34.0", "151.0","Marker in Sydney", "", ""),
                Mark("35.66", "139.6","Marker in Tokyo", "", "")
            )
        }
    }
}