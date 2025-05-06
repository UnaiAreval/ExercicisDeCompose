package cat.itb.m78.exercices.mapsApp

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState

@SuppressLint("UnrememberedMutableState")
@Composable
fun MapScreen(
    marks: List<Mark>,
    cameraPositionState: CameraPositionState,
    navigateToMarkAdition: () -> Unit
){
    GoogleMap(
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("DEMO_MAP_ID")
        },
        cameraPositionState = cameraPositionState,
        onMapLongClick = {
            navigateToMarkAdition()
        }
    ) {
        for (mark in marks){
            AdvancedMarker(
                state = MarkerState(position = LatLng(mark.latitude.toDouble(), mark.longitude.toDouble())),
                title = mark.title
            )
        }
    }
}