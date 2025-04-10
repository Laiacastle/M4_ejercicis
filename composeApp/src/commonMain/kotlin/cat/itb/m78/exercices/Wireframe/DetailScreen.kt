package cat.itb.m78.exercices.Wireframe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun DetailScreen(navigateToScreenList: () -> Unit, id: Int) {
    val model = viewModel { DetailVM(id) }
    DetailScreen(navigateToScreenList, model.pokemonDetails.value, model.spriteDetails.value, id, model::ChangeFav, model::BuscarId)


}
@Composable
fun DetailScreen(navigateToScreenList: () -> Unit, pokemonDetails: PokemonDetails?, spriteDetails: Details?, id: Int, changeFav: (Int) -> Unit, comprovarId : (Int) -> Boolean) {
    if(pokemonDetails == null || spriteDetails == null){
        CircularProgressIndicator()
    }
    else{

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally ){
            Row{
                AsyncImage(
                    spriteDetails.sprites.front,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp).clip(CircleShape).border(BorderStroke(1.dp, Color.Gray), CircleShape)
                )
                Column{
                    Text("Shiny")
                    AsyncImage(
                        spriteDetails.sprites.shiny,
                        contentDescription = null,
                        modifier = Modifier.size(200.dp).clip(CircleShape).border(BorderStroke(1.dp, Color.Gray), CircleShape)
                    )
                }
            }
            Row {

                Column {
                    Text("Name: " + pokemonDetails.name)
                    Text("Habitat: " + pokemonDetails.habitat.name)
                    if (pokemonDetails.evolutionOf != null) {
                        Text("Evolve from: " + pokemonDetails.evolutionOf.name)
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                val isFav = mutableStateOf(comprovarId(id))
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {changeFav(id)
                    isFav.value = comprovarId(id)}){
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
            OutlinedButton(onClick = { navigateToScreenList() }, modifier = Modifier.fillMaxWidth()) {
                Text("Atrás")
            }
        }
    }

}
