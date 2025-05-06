package cat.itb.m78.exercices.MapsProject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.Db.database
import catitbm78exercicisdb.Marker

class MarkersVM() : ViewModel(){
    val markQueries = database.markerQueries
    var markersList = mutableStateOf(markQueries.selectAll().executeAsList())
    fun addMarker(newMark : Marker){
        markQueries.insert(newMark.PositionX, newMark.PositionY, newMark.Title, newMark.Img)
    }
    fun updateList(){
        markersList.value = markQueries.selectAll().executeAsList()
    }

}