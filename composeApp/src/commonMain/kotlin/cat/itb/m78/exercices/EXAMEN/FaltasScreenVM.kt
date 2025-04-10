package cat.itb.m78.exercices.EXAMEN

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.m78.exercices.Db.database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaltasScreenVM(id: Int) : ViewModel() {
    var faltasQueries = database.faltasQueries
    val id = id
    val name = mutableStateOf<Student?>(null)
    var faltas= faltasQueries.find(id.toLong()).executeAsList()
    fun updateStudent() {
        faltas = faltasQueries.find(id.toLong()).executeAsList()
    }
    fun getName(id: Int){
        viewModelScope.launch(Dispatchers.Default) {
            name.value = ApiExamen.getName(id)
        }
    }
    init {
        updateStudent()
        getName(id)
    }
}