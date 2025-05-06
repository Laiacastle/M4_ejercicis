package cat.itb.m78.exercices.MapsProject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import catitbm78exercicisdb.Marker

@Composable
fun AddMarkerScreen(
    lat: Double,
    lng: Double,
    photoUri: String?,
    navigateToScreenMarkers: () -> Unit,
    navigateToScreenMap: () -> Unit
) {
    val backgroundColor = Color(0xFFF1F8E9)
    val buttonColor = Color(0xFFB2DFDB)

    val model = viewModel { MarkersVM() }

    DrawerMenu(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(backgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .align(Alignment.TopCenter),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Add New Marker",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    var title by remember { mutableStateOf("") }


                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                    )

                    Button(
                        onClick = {
                            model.addMarker(
                                Marker(
                                    Id = 0,
                                    PositionX = lat,
                                    PositionY = lng,
                                    Title = title,
                                    Img = photoUri ?: ""
                                )
                            )
                            navigateToScreenMarkers()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Save Marker")
                    }
                }
            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )
}
