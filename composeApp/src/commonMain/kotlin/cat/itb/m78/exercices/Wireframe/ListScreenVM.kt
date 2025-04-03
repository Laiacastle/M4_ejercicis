package cat.itb.m78.exercices.Wireframe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListVM : ViewModel() {

    var pokemonList = mutableStateOf<Pokedex?>(null)
    fun updateList() {
        viewModelScope.launch(Dispatchers.Default) {
            pokemonList.value = MyApi.list()
        }
    }

    init {
        updateList()
    }
}



