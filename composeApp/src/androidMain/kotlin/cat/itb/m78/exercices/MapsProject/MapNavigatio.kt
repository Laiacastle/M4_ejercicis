package cat.itb.m78.exercices.MapsProject

import CameraScreen
import MapScreen
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
    NavHost(navController = navController, startDestination = "markers") {

        composable("map") {
            MapScreen(
                onMapClick = { latLng ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("lat", latLng.latitude)
                    navController.currentBackStackEntry?.savedStateHandle?.set("lng", latLng.longitude)
                    navController.navigate("permission/${latLng.latitude}/${latLng.longitude}")
                },
                navigateToScreenMarkers = { navController.navigate("markers") },
                navigateToScreenMap = { navController.navigate("map") }
            )
        }

        composable("permission/{lat}/{lng}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lng = backStackEntry.arguments?.getString("lng")?.toDoubleOrNull() ?: 0.0

            FeatureThatRequiresCameraPermission(
                navigateToScreenCamera = {
                    navController.navigate("camera/${lat}/${lng}")
                }
            )
        }

        composable("camera/{lat}/{lng}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lng = backStackEntry.arguments?.getString("lng")?.toDoubleOrNull() ?: 0.0

            CameraScreen(
                navigateBackWithPhoto = { uri ->
                    navController.navigate("add_marker/${lat}/${lng}?photo_uri=$uri")
                },
                navigateToScreenMarkers = { navController.navigate("markers") },
                navigateToScreenMap = { navController.navigate("map") }
            )
        }

        composable("add_marker/{lat}/{lng}?photo_uri={photo_uri}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lng = backStackEntry.arguments?.getString("lng")?.toDoubleOrNull() ?: 0.0
            val photoUri = backStackEntry.arguments?.getString("photo_uri")

            AddMarkerScreen(
                lat = lat,
                lng = lng,
                photoUri = photoUri,
                navigateToScreenMarkers = { navController.navigate("markers") },
                navigateToScreenMap = { navController.navigate("map") }
            )
        }

        composable("markers") {
            MarkersScreen(
                navigateToScreenMap = { navController.navigate("map") },
                navigateToScreenMarkers = { navController.navigate("markers") },
                navigateToScreenAddMarkers = {
                    navController.navigate("map") // <<< empieza el flujo desde el mapa
                }
            )
        }
    }
}