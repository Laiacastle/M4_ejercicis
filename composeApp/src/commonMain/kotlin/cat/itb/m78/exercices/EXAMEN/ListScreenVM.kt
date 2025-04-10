package cat.itb.m78.exercices.EXAMEN

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.Db.database
import cat.itb.m78.exercices.Wireframe.MyApi
import cat.itb.m78.exercices.Wireframe.Pokedex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListScreenVM : ViewModel() {
    val faltasQueries = database.faltasQueries
    var studentsList = mutableStateOf<List<Student>?>(null)
    fun updateList() {
        viewModelScope.launch(Dispatchers.Default) {
            studentsList.value = ApiExamen.list()
        }
    }
    fun addFalta(id: Int){
        faltasQueries.insert(id.toLong())
    }
    fun getFaltas(id: Int): Int {
        val falt = faltasQueries.selectAll().executeAsList()
        var count = mutableStateOf(0)
        for(falta in falt) {
            if (falta.Id == id.toLong()) {
                count.value++
            }
        }
        return count.value
    }

    init {
        updateList()
    }
}