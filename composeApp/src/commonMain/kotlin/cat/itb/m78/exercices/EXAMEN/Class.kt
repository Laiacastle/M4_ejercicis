package cat.itb.m78.exercices.EXAMEN

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import m78exercices.composeapp.generated.resources.Res

@Serializable
data class Student (
    @SerialName("id") val id : Int,
    @SerialName("name") val name : String,
    @SerialName("surnames") val surname: String,
    @SerialName("email") val email: String,
    @SerialName("photo_link") val img: String
)

