package cat.itb.m78.exercices.MapsProject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

@Composable
fun MarkersScreen(
    navigateToScreenMap: () -> Unit,
    navigateToScreenMarkers: () -> Unit,
    navigateToScreenAddMarkers: () -> Unit
) {
    //  colors
    val pastelCardColor = Color(0xFFFFF3E0)
    val pastelImageBg = Color(0xFFE1F5FE)
    val buttonColor = Color(0xFFB2DFDB)
    val deleteButtonColor = Color(0xFFFFCDD2)

    val model = viewModel { MarkersVM() }
    model.updateList()
    val markers = model.markersList.value

    DrawerMenu(
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFF1F8E9))
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        text = "Markers List",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    var text by remember  {mutableStateOf("")}
                    Row(modifier = Modifier.fillMaxWidth()){

                        OutlinedTextField(
                            value = text,
                            onValueChange = { text = it },
                            label = { Text("Search") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .padding(20.dp)
                        )
                    }
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)

                    ) {
                        val filter = markers.filter{it.Title.toString().contains(text, ignoreCase = true)}
                        if(filter.isEmpty()){
                            item{
                                Text("No PokeStop found")
                            }
                        }
                        itemsIndexed(filter){ _, mark ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = pastelCardColor),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(text = mark.Title.toString(), style = MaterialTheme.typography.bodyLarge)
                                        Text(text = "Lat: ${mark.PositionX}", style = MaterialTheme.typography.bodySmall)
                                        Text(text = "Lng: ${mark.PositionY}", style = MaterialTheme.typography.bodySmall)
                                    }

                                    Box(
                                        modifier = Modifier
                                            .padding(start = 16.dp)
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(pastelImageBg)
                                    ) {
                                        AsyncImage(
                                            model = mark.Img,
                                            contentDescription = "Marker Image",
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    // Delete Button
                                    IconButton(
                                        onClick = {
                                            model.removeMarker(mark.Id)  // Remove the selected marker
                                        },
                                        modifier = Modifier.padding(start = 16.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete Marker",
                                            tint = deleteButtonColor
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = navigateToScreenAddMarkers,
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text("Add Marker")
                        }
                    }
                }
            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )
}
