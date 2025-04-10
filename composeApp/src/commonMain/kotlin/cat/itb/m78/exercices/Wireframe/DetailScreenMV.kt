package cat.itb.m78.exercices.Wireframe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.Db.database
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class DetailVM(id: Int) : ViewModel() {
    private val  favQueries = database.favoritesQueries
    var pokemonDetails = mutableStateOf<PokemonDetails?>(null)
    var spriteDetails = mutableStateOf<Details?>(null)
    val listIds = mutableStateOf(favQueries.selectIds().executeAsList())
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
    fun BuscarId(id: Int): Boolean{

        for(idss in listIds.value){
            if(id.toLong() == idss){
                return true
            }
        }
        return false
    }
    fun ChangeFav(id: Int){
        viewModelScope.launch(Dispatchers.Default) {

            if(BuscarId(id)){
                favQueries.delete(id.toLong())
            }else{
                favQueries.insert(id.toLong(), pokemonDetails.value!!.name, pokemonDetails.value!!.habitat.name, spriteDetails.value!!.sprites.front)
            }
            listIds.value = favQueries.selectIds().executeAsList()
        }
    }


}