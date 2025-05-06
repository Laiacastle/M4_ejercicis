package cat.itb.m78.exercices.MapsProject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import catitbm78exercicisdb.Marker
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(navigateToScreenMarkers: () -> Unit, navigateToScreenMap: ()-> Unit){
    val model = viewModel{ MarkersVM() }
    DrawerMenu (
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Map( model.markersList.value)

            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )

}

@Composable
fun Map( markers: List<Marker>){
    GoogleMap(
        googleMapOptionsFactory = {
            GoogleMapOptions().mapId("DEMO_MAP_ID")
        },
    ) {
        markers.forEach { marker ->
            val markerState = remember { MarkerState(position = LatLng(marker.PositionX, marker.PositionY)) }

            AdvancedMarker(
                state = markerState,
                title = marker.Title
            )

    }}
}