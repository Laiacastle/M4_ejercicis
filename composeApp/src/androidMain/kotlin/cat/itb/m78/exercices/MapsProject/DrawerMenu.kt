package cat.itb.m78.exercices.MapsProject

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun DrawerMenu(
    content: @Composable (PaddingValues) -> Unit,
    navigateToScreenMap: () -> Unit,
    navigateToScreenMarkers: () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        "PokeStops",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleLarge
                    )
                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = { Text("Map") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.LocationOn, contentDescription = null) },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navigateToScreenMap()
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Markers") },
                        selected = false,
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        badge = { Text("20") },
                        onClick = {
                            scope.launch { drawerState.close() }
                            navigateToScreenMarkers()
                        },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                IconButton(
                    onClick = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open()
                            else drawerState.close()
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }

                Column {
                    Spacer(modifier = Modifier.height(56.dp))
                    content(PaddingValues())
                }
            }
        }
    }
}