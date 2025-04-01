package cat.itb.m78.exercices.Wireframe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class DetailVM(id: Int) : ViewModel() {

    var pokemonDetails = mutableStateOf<PokemonDetails?>(null)
    var spriteDetails = mutableStateOf<Details?>(null)
    val id = id;
    init{
        updateList()
    }
    fun updateList() {
        viewModelScope.launch(Dispatchers.Default) {
            pokemonDetails.value = MyApi.pokemonList(id)
            spriteDetails.value = MyApi.detailsList(id)
        }
    }


}