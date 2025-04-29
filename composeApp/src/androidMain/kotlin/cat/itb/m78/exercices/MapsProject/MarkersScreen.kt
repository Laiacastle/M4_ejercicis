package cat.itb.m78.exercices.MapsProject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun MarkersScreen(navigateToScreenMap: () -> Unit, navigateToScreenMarkers: () -> Unit, navigateToScreenAddMarkers: () -> Unit) {
    DrawerMenu (
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                val model = viewModel{ MarkersVM() }
                model.updateList()
                val markers = model.markersList.value
                LazyColumn(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    itemsIndexed(markers) { _, mark ->
                        Text(text = mark.Title.toString())
                    }
                }
                    Button(onClick = navigateToScreenAddMarkers) { Text("Add Marker") }

            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )
}
