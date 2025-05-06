package cat.itb.m78.exercices.MapsProject


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MarkersScreen(navigateToScreenMap: () -> Unit, navigateToScreenMarkers: () -> Unit, navigateToScreenAddMarkers: () -> Unit) {
    DrawerMenu (
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
            Column(
                modifier = Modifier.padding(innerPadding).align(Alignment.Center) )
            {
                val model = viewModel{ MarkersVM() }
                model.updateList()
                val markers = model.markersList.value
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    Text("Title")
                    Text("Position X")
                    Text("Position Y")
                }
                LazyColumn(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    itemsIndexed(markers) { _, mark ->
                        Card(){
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically ){
                                Text(text = mark.Title.toString())
                                Text(text = mark.PositionX.toString() )
                                Text(text = mark.PositionY.toString())
                            }
                        }


                    }
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.Center){
                    Button(onClick = navigateToScreenAddMarkers) { Text("Add Marker") }
                }


            }}
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )
}
