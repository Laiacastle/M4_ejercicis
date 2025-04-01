package cat.itb.m78.exercices.Wireframe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListVM : ViewModel() {

    var pokemonList : Pokedex? = null
    fun updateList() {
        viewModelScope.launch(Dispatchers.Default) {
            pokemonList = MyApi.list()
        }
    }
    init {
        updateList()
        }
    }



