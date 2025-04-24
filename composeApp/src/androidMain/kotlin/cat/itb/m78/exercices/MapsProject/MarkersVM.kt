package cat.itb.m78.exercices.MapsProject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.Db.database

class MarkersVM() : ViewModel(){
    val markQueries = database.markersqueries
    var markersList = markQueries.selectAll().executeAsList()

}