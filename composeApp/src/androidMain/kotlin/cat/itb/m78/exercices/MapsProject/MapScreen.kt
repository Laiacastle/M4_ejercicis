import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import com.google.android.gms.maps.model.LatLng
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.MapsProject.DrawerMenu
import cat.itb.m78.exercices.MapsProject.MarkersVM
import com.google.android.gms.maps.GoogleMapOptions
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
@Composable
fun MapScreen(
    onMapClick: (LatLng) -> Unit,
    navigateToScreenMarkers: () -> Unit,
    navigateToScreenMap: () -> Unit
) {
    val model = viewModel { MarkersVM() }
    var showDialog by remember { mutableStateOf(false) }
    var clickedPosition by remember { mutableStateOf<LatLng?>(null) }

    DrawerMenu(
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                GoogleMap(
                    googleMapOptionsFactory = {
                        GoogleMapOptions().mapId("DEMO_MAP_ID")
                    },
                    onMapClick = { latLng ->
                        clickedPosition = latLng
                        showDialog = true
                    }
                ) {
                    model.markersList.value.forEach { marker ->
                        val markerState = remember {
                            MarkerState(position = LatLng(marker.PositionX, marker.PositionY))
                        }
                        AdvancedMarker(state = markerState, title = marker.Title)
                    }
                }

                // Diálogo de confirmación
                if (showDialog && clickedPosition != null) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        confirmButton = {
                            TextButton(onClick = {
                                showDialog = false
                                onMapClick(clickedPosition!!) // confirmación: continúa con la lógica original
                            }) {
                                Text("Sí")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("No")
                            }
                        },
                        title = { Text("¿Añadir nuevo marcador?") },
                        text = { Text("¿Deseas crear un nuevo marcador en esta ubicación?") }
                    )
                }
            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )
}