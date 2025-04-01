package cat.itb.m78.exercices.Wireframe

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName("name") val name: String,
    @SerialName("url") val url : String)

@Serializable
data class PokemonEntries(
    @SerialName("entry_number") val entryNum: Int,
    @SerialName("pokemon_species") val pokemonSpecies: Pokemon)

@Serializable
data class Pokedex(
    @SerialName("pokemon_entries") val pokemonEntries: List<PokemonEntries>
)

//DETAILS
//URL BASE
@Serializable
data class PokemonDetails(
    @SerialName("evolves_from_species") val evolutionOf: Evolve,
    @SerialName("habitat") val habitat: Habitat,
    @SerialName("name") val name: String
)

@Serializable
data class Evolve(
    @SerialName("name") val name: String
)

@Serializable
data class Habitat(
    @SerialName("name") val name:String
)

//REPLACE URL
@Serializable
data class Details(
    @SerialName("sprites") val sprites: Sprites,
    @SerialName("types") val types: List<TypeList>
)

@Serializable
data class Sprites(
    @SerialName("front_default") val front: String,
    @SerialName("front_shiny") val shiny: String
)

@Serializable
data class TypeList(
    @SerialName("type") val type: Type
)

@Serializable
data class Type(
    @SerialName("name") val name: String
)


