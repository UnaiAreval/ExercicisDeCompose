package cat.itb.m78.exercices.mapsApp

import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.compose.CameraXViewfinder
import androidx.camera.core.SurfaceRequest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.toRoute
import cat.itb.m78.exercices.p2.FeatureThatRequiresCameraPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.cameraIcon
import org.jetbrains.compose.resources.painterResource

object MapNavigation{
    @Serializable
    data object PrincipalScreen
    @Serializable
    data class AddMark(val lat: Float, val lng: Float)
    @Serializable
    data object Camera
}

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun MapAppScreen(){
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

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
                        cameraPositionState =  cameraPositionState,
                        navigateToMarkAdition = { navController.navigate(MapNavigation.AddMark(
                            lat = cords.value.latitude.toFloat(),
                            lng = cords.value.longitude.toFloat()))
                        }
                    )
                }
            }
            composable<MapNavigation.AddMark> { backStack -> 
                val cs = LatLng(
                    backStack.toRoute<MapNavigation.AddMark>().lat.toDouble(),
                    backStack.toRoute<MapNavigation.AddMark>().lng.toDouble()
                )
                AddMarkToTheMap(
                    cords = cs,
                    backToTheMap = { navController.navigate(MapNavigation.PrincipalScreen) },
                    kipImageUri = { mapViewModel.KippLastUri(it) },
                    addNewMark = { mapViewModel.AddNewMark(it) },
                    lastImageUri = mapViewModel.kippUri
                )
            }
            composable<MapNavigation.Camera> {
                if (cameraPermissionState.status.isGranted){
                    //et porta a la camera
                    Text("No implementat")
                    Button(onClick = { navController.navigate(MapNavigation.PrincipalScreen) }) {
                        Text("Tornar enrrera")
                    }
                }
                else{
                    FeatureThatRequiresCameraPermission()
                }
            }
        }
    }
}

data class Mark(
    val latitude: String,
    val longitude: String,
    val title: String,
    val imageUri: String,
    val description: String?
)


private const val LAST_URI_KIPPER_KEY = "uri"
class MapViewModel : ViewModel(){
    val marks: MutableState<List<Mark>?> = mutableStateOf(null)
    val settings: Settings = Settings()
    val kippUri = settings.getString(LAST_URI_KIPPER_KEY, "")

    val newMark: MutableState<Mark?> = mutableStateOf(null)

    init {
        viewModelScope.launch {
            marks.value = listOf(
                Mark("41.453583295083675", "2.186311455073678", "Institut Tecnològic de Barcelona", "https://dca.cat/wp-content/uploads/2022/05/ITB_Logo_ITBdesc_800-Direccio-ITB-Alberto-Vila.png", ""),
                Mark("-34.0", "151.0","Marker in Sydney", "", ""),
                Mark("35.66", "139.6","Marker in Tokyo", "", "")
            )
        }
    }
    fun KippLastUri(lastUri: String){
        settings[LAST_URI_KIPPER_KEY] = lastUri
    }
    fun AddNewMark(markToAdd: Mark){
        if (markToAdd.description == null){
            newMark.value = Mark(markToAdd.latitude, markToAdd.longitude, markToAdd.title, markToAdd.imageUri, "No hi ha descripció")
        }
        else{
            newMark.value = markToAdd
        }
        //afegirla a la base de dades
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapCamera(
    takePhoto: (Context)-> Unit,
    surfaceRequest: SurfaceRequest?,
    context: Context
){
    Scaffold (bottomBar = {
        BottomAppBar (
            actions = {
                NavigationBarItem(
                    onClick = {
                        takePhoto(context)
                    },
                    selected = false,
                    icon = {
                        Icon(
                            painterResource(Res.drawable.cameraIcon),
                            contentDescription = "camera icon",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    label = { Text("Take Photo") }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
    }) {
        surfaceRequest?.let { request ->
            CameraXViewfinder(
                surfaceRequest = request,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}