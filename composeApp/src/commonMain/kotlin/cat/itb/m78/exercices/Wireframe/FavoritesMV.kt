package cat.itb.m78.exercices.Wireframe

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.Db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesMV : ViewModel() {
    val favQueries = database.favoritesQueries
    var favoritesList = mutableStateOf(favQueries.selectAll().executeAsList())
    fun DeleteFromFav(id: Long){
        favQueries.delete(id)
        favoritesList.value = favQueries.selectAll().executeAsList()
    }
}