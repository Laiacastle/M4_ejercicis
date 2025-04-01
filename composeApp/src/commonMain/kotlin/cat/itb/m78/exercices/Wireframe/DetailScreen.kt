package cat.itb.m78.exercices.Wireframe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

@Composable
fun DetailScreen(navigateToScreenList: () -> Unit, id: Int) {
    val model = viewModel { DetailVM(id) }
    DetailScreen(navigateToScreenList, model.pokemonDetails.value, model.spriteDetails.value)


}
@Composable
fun DetailScreen(navigateToScreenList: () -> Unit, pokemonDetails: PokemonDetails?, spriteDetails: Details?) {
    if (pokemonDetails != null && spriteDetails != null) {

        Column {
            AsyncImage(
                spriteDetails.sprites.front,
                contentDescription = null,
                modifier = Modifier.height(60.dp).width(80.dp)
            )
            Text(pokemonDetails.name)
            Text(pokemonDetails.habitat.name)
            Text(pokemonDetails.evolutionOf.name)

            Button(onClick = { navigateToScreenList() }) {
                Text("Atrás")
            }
        }
    } else {
        Text("Error nulo")
    }

}
