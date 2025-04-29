package cat.itb.m78.exercices.mapsApp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapScreen(
    marks: List<Mark>,
    cameraPositionState: CameraPositionState,
    cords: LatLng,
    navigateToMarkAdition: (List<Double>) -> Unit
){
    GoogleMap(
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("DEMO_MAP_ID")
        },
        cameraPositionState = cameraPositionState/*,
        onMapLongClick = {
            navigateToMarkAdition(listOf(cords.latitude, cords.longitude))
        }*/
    ) {
        for (mark in marks){
            AdvancedMarker(
                state = MarkerState(position = LatLng(mark.latitude.toDouble(), mark.longitude.toDouble())),
                title = mark.title
            )
        }
    }
}