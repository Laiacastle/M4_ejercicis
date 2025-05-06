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
    @Serializable
    data class  AddMarkerScreen(val photo: String)
}

@Composable
fun MapNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationMap.MarkersScreen) {
        composable<DestinationMap.PermissionScreen> {
            FeatureThatRequiresCameraPermission(
                navigateToScreenCamera = { navController.navigate(DestinationMap.CameraScreen) }
            )
        }
        composable<DestinationMap.MarkersScreen> {
            MarkersScreen(
                navigateToScreenMap = { navController.navigate(DestinationMap.MapScreen) },
                navigateToScreenMarkers = { navController.navigate(DestinationMap.MarkersScreen) },
                navigateToScreenAddMarkers = { navController.navigate(DestinationMap.AddMarkerScreen) }
            )
        }
        composable<DestinationMap.MapScreen> {
            MapScreen(
                navigateToScreenMarkers = { navController.navigate(DestinationMap.MarkersScreen) },
                navigateToScreenMap = { navController.navigate(DestinationMap.MapScreen) }
            )
        }
        composable<DestinationMap.CameraScreen> {
            CameraScreen(
                navigateToScreenMarkers = { navController.navigate(DestinationMap.MarkersScreen) },
                navigateToScreenMap = { navController.navigate(DestinationMap.MapScreen) }
            )
        }
        composable<DestinationMap.AddMarkerScreen> {
            AddMarkerScreen(
                navigateToScreenMarkers = { navController.navigate(DestinationMap.MarkersScreen) },
                navigateToScreenMap = { navController.navigate(DestinationMap.MapScreen) },
                navigateToScreenPermission = { navController.navigate(DestinationMap.PermissionScreen) }

            )
        }
    }
}