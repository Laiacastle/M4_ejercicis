package cat.itb.m78.exercices.MapsProject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.Db.database

class MarkersVM() : ViewModel(){
    val markQueries = database.markerQueries
    var markersList = mutableStateOf(markQueries.selectAll().executeAsList())
    fun addMarker(newMark : Markers){
        markQueries.insert(newMark.positionX, newMark.positionY, newMark.title, newMark.img)
    }
    fun updateList(){
        markersList.value = markQueries.selectAll().executeAsList()
    }

}