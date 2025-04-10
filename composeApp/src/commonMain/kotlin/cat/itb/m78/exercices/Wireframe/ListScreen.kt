package cat.itb.m78.exercices.Wireframe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ListScreen (navigateToScreenDetails: (Int) -> Unit){
    val model = viewModel{ ListVM() }
    ListScreen(navigateToScreenDetails, model.pokemonList.value, model::changeFavorite, model::BuscarId)
}

@Composable
fun ListScreen(navigateToScreenDetails: (Int) -> Unit, pokemonList: Pokedex?, changeToFavs: (Int) -> Unit, comprovarId: (Int) -> Boolean){
    var text by remember  {mutableStateOf("")}
    Column(modifier = Modifier.padding(end = 15.dp).fillMaxWidth().padding(10.dp), verticalArrangement = Arrangement.spacedBy(15.dp), horizontalAlignment = Alignment.CenterHorizontally){

        Row(modifier = Modifier.fillMaxWidth()){

            TextField(value = text,
                onValueChange = { text = it },
                label = { Text("Search") },
                modifier = Modifier.width(600.dp)
                )
        }

        if(pokemonList == null){
            CircularProgressIndicator()
        }
        else{
            LazyColumn (modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val filter = pokemonList.pokemonEntries.filter{it.pokemonSpecies.name.contains(text, ignoreCase = true)}
                if(filter.isEmpty()){
                    item{
                        Text("No s'ha trobat cap pokemon")
                    }
                }
                itemsIndexed(filter) { _, pokemon ->
                    val isFav = mutableStateOf(comprovarId(pokemon.entryNum))
                    Card(modifier = Modifier.fillMaxWidth()){
                        Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {

                                Button(
                                    onClick = { navigateToScreenDetails(pokemon.entryNum) },
                                    modifier = Modifier
                                ) {
                                    Text(pokemon.pokemonSpecies.name)
                                }
                        Row(modifier = Modifier, horizontalArrangement = Arrangement.End) {
                                IconButton(onClick = {changeToFavs(pokemon.entryNum)
                                isFav.value = comprovarId(pokemon.entryNum)}) {

                                        if (isFav.value) {
                                            Icon(
                                                Icons.Default.Star,
                                                tint = Color.Yellow,
                                                contentDescription = ""
                                            )


                                        } else {
                                            Icon(
                                                Icons.Default.Star,
                                                tint = Color.Gray,
                                                contentDescription = ""
                                            )


                                        }


                                }
                            }
                        }
                    }

                }
            }

     }
}}