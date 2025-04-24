package cat.itb.m78.exercices.p2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.serialization.Serializable

object CameraApp {
    @Serializable
    data object Camera
    @Serializable
    data object PhotosGaleri
    @Serializable
    data class Photo(val photoUrl: String)
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(){
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    val viewModel = viewModel{ CameraViewModel() }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        viewModel.bindToCamera(context.applicationContext, lifecycleOwner)
    }

    val surfaceRequest = viewModel.surferRequest.value

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination =  CameraApp.Camera ) {
        composable<CameraApp.Camera> {
            if (cameraPermissionState.status.isGranted){
                Camera(
                    navigateToPhotosList = { navController.navigate(CameraApp.PhotosGaleri) },
                    takePhoto = { viewModel.takePhoto(context) },
                    surfaceRequest = surfaceRequest,
                    context = context
                )
            }else{
                FeatureThatRequiresCameraPermission()

            }
        }
        composable<CameraApp.PhotosGaleri> {
            ViewPhotos(
                navigateToCamera = { navController.navigate(CameraApp.Camera) }
            )
        }
        composable<CameraApp.Photo> {  }
    }
}