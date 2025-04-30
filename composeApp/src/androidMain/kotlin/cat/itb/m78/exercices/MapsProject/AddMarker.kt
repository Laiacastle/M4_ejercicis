package cat.itb.m78.exercices.MapsProject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddMarkerScreen(navigateToScreenPermission:()->Unit, navigateToScreenMarkers: ()->Unit, navigateToScreenMap: () -> Unit){
    DrawerMenu (
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                var textX by remember{mutableStateOf("")}
                var textY by remember{mutableStateOf("")}
                var text by remember {mutableStateOf("")}
                TextField(
                    value = textX,
                    onValueChange = { textX = it },
                    label = { Text("Coordenadas X") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )

                TextField(
                    value = textY,
                    onValueChange = { textY = it },
                    label = { Text("Coordenadas Y") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                )
                Button(onClick = navigateToScreenPermission) {
                    Text("Add a photo")
                }
            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )

}