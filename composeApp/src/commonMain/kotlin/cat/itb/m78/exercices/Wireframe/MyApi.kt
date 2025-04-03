package cat.itb.m78.exercices.Wireframe

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object MyApi {
    private const val URL = "https://pokeapi.co/api/v2/pokedex/2/"
    private const val URL_DETAIL = "https://pokeapi.co/api/v2/pokemon-species"
    private const val URL_DETAIL_2 = "https://pokeapi.co/api/v2/pokemon"
    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get(URL).body<Pokedex>()
    suspend fun pokemonList(id : Int) =  client.get("$URL_DETAIL/$id/").body<PokemonDetails>()
    suspend fun detailsList(id: Int) = client.get("$URL_DETAIL_2/$id/").body<Details>()
}