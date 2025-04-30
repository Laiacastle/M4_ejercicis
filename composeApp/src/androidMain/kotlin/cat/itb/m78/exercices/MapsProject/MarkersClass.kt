package cat.itb.m78.exercices.MapsProject

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Markers (
    @SerialName("Id") val id : Int,
    @SerialName("PositionX") val positionX : Double,
    @SerialName("PositionY") val positionY : Double,
    @SerialName("Title") val title : String,
    @SerialName("Img") val img : String
)