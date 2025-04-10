package cat.itb.m78.exercices.EXAMEN

import cat.itb.m78.exercices.Wireframe.Details
import cat.itb.m78.exercices.Wireframe.Pokedex
import cat.itb.m78.exercices.Wireframe.PokemonDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiExamen {
        private const val URL = "https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json"
        private val client = HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        suspend fun list() = client.get(URL).body<List<Student>>()
        suspend fun getName(id:Int) = client.get("$URL?id=$id").body<Student?>()
}
