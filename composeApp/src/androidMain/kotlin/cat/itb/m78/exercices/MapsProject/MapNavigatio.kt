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
    data class PermissionScreen(val lat: Double, val lng: Double) {
        fun route() = "permission/$lat/$lng"
    }

    @Serializable
    data class CameraScreen(val lat: Double, val lng: Double) {
        fun route() = "camera/$lat/$lng"
    }

    @Serializable
    data object MapScreen {
        fun route() = "map"
    }

    @Serializable
    data object MarkersScreen {
        fun route() = "markers"
    }

    @Serializable
    data class AddMarkerScreen(val lat: Double, val lng: Double, val photo: String?) {
        fun route() = "add_marker/$lat/$lng?photo_uri=${photo ?: ""}"
    }
}

@Composable
fun MapNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationMap.MarkersScreen.route()) {

        composable("map") {
            MapScreen(
                onMapClick = { latLng ->
                    navController.navigate(DestinationMap.PermissionScreen(latLng.latitude, latLng.longitude).route())
                },
                navigateToScreenMarkers = {
                    navController.navigate(DestinationMap.MarkersScreen.route())
                },
                navigateToScreenMap = {
                    navController.navigate(DestinationMap.MapScreen.route())
                }
            )
        }

        composable("permission/{lat}/{lng}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lng = backStackEntry.arguments?.getString("lng")?.toDoubleOrNull() ?: 0.0

            FeatureThatRequiresCameraPermission(
                navigateToScreenCamera = {
                    navController.navigate(DestinationMap.CameraScreen(lat, lng).route())
                }
            )
        }

        composable("camera/{lat}/{lng}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: 0.0
            val lng = backStackEntry.arguments?.getString("lng")?.toDoubleOrNull() ?: 0.0

            CameraScreen(
                navigateBackWithPhoto = { uri ->
                    navController.navigate(DestinationMap.AddMarkerScreen(lat, lng, uri).route())
                },
                navigateToScreenMarkers = {
                    navController.navigate(DestinationMap.MarkersScreen.route())
                },
                navigateToScreenMap = {
                    navController.navigate(DestinationMap.MapScreen.route())
                }
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
                navigateToScreenMarkers = {
                    navController.navigate(DestinationMap.MarkersScreen.route())
                },
                navigateToScreenMap = {
                    navController.navigate(DestinationMap.MapScreen.route())
                }
            )
        }

        composable("markers") {
            MarkersScreen(
                navigateToScreenMap = {
                    navController.navigate(DestinationMap.MapScreen.route())
                },
                navigateToScreenMarkers = {
                    navController.navigate(DestinationMap.MarkersScreen.route())
                },
                navigateToScreenAddMarkers = {
                    navController.navigate(DestinationMap.MapScreen.route())
                }
            )
        }
    }
}