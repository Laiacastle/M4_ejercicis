package cat.itb.m78.exercices.Wireframe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.Db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListVM : ViewModel() {

    var pokemonList = mutableStateOf<Pokedex?>(null)
    var pokemon = mutableStateOf<PokemonDetails?>(null)
    fun updateList() {
        viewModelScope.launch(Dispatchers.Default) {
            pokemonList.value = MyApi.list()
        }
    }

    fun BuscarId(id: Int): Boolean{
        val favQueries = database.favoritesQueries
        val ids = favQueries.selectIds().executeAsList()
        for(idss in ids){
            if(id.toLong() == idss){
                return true

            }
        }
        return false
    }
    fun addFavorite(id : Int){
        viewModelScope.launch(Dispatchers.Default) {
            val pokemonDetails = MyApi.pokemonList(id)
            pokemon.value = pokemonDetails
            val favQueries = database.favoritesQueries
            if(BuscarId(id)){
                favQueries.delete(id.toLong())
            }else{
                favQueries.insert(id.toLong(), pokemonDetails.name, pokemonDetails.habitat.name)
            }



        }
    }
    init {
        updateList()
    }
}



