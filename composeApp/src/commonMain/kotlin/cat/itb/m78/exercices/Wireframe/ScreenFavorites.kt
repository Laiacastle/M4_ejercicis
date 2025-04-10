package cat.itb.m78.exercices.Wireframe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun ScreenFavorites(navigateToScreenList:()->Unit, navigateToScreendetails: (Int) -> Unit){
    val model = viewModel{ FavoritesMV() }
    val favorites = model.favoritesList.value
    Column (
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        LazyColumn(
            modifier = Modifier.padding(15.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp),

        ) {
            itemsIndexed(favorites) { _, favorite ->
                Card(modifier = Modifier.fillMaxWidth().clickable(onClick = {navigateToScreendetails(favorite.Pokemon_id.toInt())})){

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally)
                        {
                            AsyncImage(

                                favorite.Img,
                                contentDescription = null,
                                modifier = Modifier.size(300.dp).clip(CircleShape).border(
                                    BorderStroke(1.dp, Color.Gray), CircleShape
                                )
                            )
                            Text("Name: " + favorite.Name)
                            Text("Habitat: " + favorite.Habitat.toString())

                        }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
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
        }
        Button(onClick = navigateToScreenList) {
            Text("<--")
        }
    }




