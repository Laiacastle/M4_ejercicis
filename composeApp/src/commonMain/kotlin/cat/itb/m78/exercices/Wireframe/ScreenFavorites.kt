package cat.itb.m78.exercices.Wireframe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScreenFavorites(navigateToScreenList:()->Unit){
    val model = viewModel{ FavoritesMV() }
    val favorites = model.favoritesList.value
    Column (
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(favorites) { _, favorite ->
                Card(modifier = Modifier.fillMaxWidth()){

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            Text("Name: " + favorite.Name)
                            Text("Type: " + favorite.Type.toString())
                        }
                        IconButton(onClick = {model.DeleteFromFav(favorite.Pokemon_id)}){
                            Icon(
                                imageVector = Icons.Default.Star,
                                tint = Color.Yellow,
                                contentDescription = null
                            )
                        }

                    }

                }

            }
        }
        Button(onClick = navigateToScreenList) {
            Text("<--")
        }
    }




