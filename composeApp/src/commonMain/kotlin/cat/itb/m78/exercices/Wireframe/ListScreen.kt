package cat.itb.m78.exercices.Wireframe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
fun ListScreen(navigateToScreenDetails: (Int) -> Unit){
    val model = viewModel{ ListVM() }

    Column{

        var text by remember  {mutableStateOf("")}
        TextField(value = text,
            onValueChange = { text = it },
            label = { Text("Text") })
        if(model.pokemonList == null){
            CircularProgressIndicator()
        }
        else{
            LazyColumn (modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                itemsIndexed(model.pokemonList!!.pokemonEntries) { _, pokemon ->

                    Button(onClick = {navigateToScreenDetails(pokemon.entryNum)}){
                        Text(pokemon.pokemonSpecies.name)
                    }

                }

        }

    }
}}