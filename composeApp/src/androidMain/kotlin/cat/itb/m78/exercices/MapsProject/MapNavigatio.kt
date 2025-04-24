package cat.itb.m78.exercices.MapsProject

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.Camera.DestinationCamera
import cat.itb.m78.exercices.Camera.ScreenPhotos
import kotlinx.serialization.Serializable

object DestinationMap {
    @Serializable
    data object PermissionScreen
    @Serializable
    data object CameraScreen
    @Serializable
    data object MapScreen
    @Serializable
    data object MarkersScreen
}

@Composable
fun MapNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationMap.PermissionScreen) {
        composable<DestinationMap.PermissionScreen>{
            FeatureThatRequiresCameraPermission(
                navigateToScreenMarkers= { navController.navigate(DestinationMap.MarkersScreen) }
            )
        }
        composable<DestinationMap.MarkersScreen> {

            MarkersScreen(
                navigateToScreenMap = {navController.navigate((DestinationMap.MapScreen))},
                navigateToScreenCamera = {navController.navigate((DestinationMap.CameraScreen))}
                )
        }
        composable<DestinationMap.MapScreen> {

            MapScreen(
                navigateToScreenMarkers = {navController.navigate((DestinationMap.MarkersScreen))},
                navigateToScreenCamera = {navController.navigate((DestinationMap.CameraScreen))}
                )
        }
        composable<DestinationMap.CameraScreen> {

            CameraScreen(
                navigateToScreenMarkers = {navController.navigate((DestinationMap.CameraScreen))},
                navigateToScreenMap = {navController.navigate((DestinationMap.MapScreen))}
                )
        }

    }
}