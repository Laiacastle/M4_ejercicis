package cat.itb.m78.exercices.MapsProject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import cat.itb.m78.exercices.Db.database
import catitbm78exercicisdb.Marker


class MarkersVM() : ViewModel(){
    val markQueries = database.markerQueries
    var markersList = mutableStateOf(markQueries.selectAll().executeAsList())
    fun addMarker(newMark: Marker) {
        markQueries.insert(
            newMark.PositionX,
            newMark.PositionY,
            newMark.Title,
            newMark.Img
        )
        updateList()
    }
    fun updateList(){
        markersList.value = markQueries.selectAll().executeAsList()
    }
    fun removeMarker(id  : Long){
        markQueries.delete(id)
        updateList()
    }
    init{
        //Ponemos las pokeparadas (he puestoa lgunas de eespana y peru)
        val pokeMarkers = listOf(
            Marker(1, 37.377148,-5.987055,"Parque de María Luisa, Sevilla", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_1.jpg" ),
            Marker(2, 41.661545,-0.894701,"Parque de la Química, Zaragoza", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_2.jpg" ),
            Marker(3, 40.417543,-3.682446,"Parque de El Retiro, Madrid", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_3.jpg" ),
            Marker(4, 41.496442,2.132799,"Cerdanyola del Vallès, Barcelona", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_4.jpg" ),
            Marker(5, 37.588593,-0.976493,"Murcia, Cartagena", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_5.jpg" ),
            Marker(6, 38.269842,-0.698323,"Parque Municipal, Alicante", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_6.jpg" ),
            Marker(7, 38.098771,-3.632003,"Paseo de Linarejos, Linares", "https://comunidadflyoficial.com/assets/img/place/espana_lugar_7.jpg" ),
            Marker(8, -11.563121,-77.270121,"Plaza de Armas de Chancay, Chancay", "https://comunidadflyoficial.com/assets/img/place/peru_lugar_1.jpg" ),
            Marker(9, -9.530584,-77.528815,"Plaza de Armas de Huaraz, Huaraz", "https://comunidadflyoficial.com/assets/img/place/peru_lugar_2.jpg" ),)

        for(i in pokeMarkers){
            markQueries.insert(i.PositionX, i.PositionY, i.Title, i.Img)
        }
    }

}