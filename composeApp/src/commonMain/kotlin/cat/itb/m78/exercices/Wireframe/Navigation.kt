package cat.itb.m78.exercices.Wireframe

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object DestinationPokemon {
    @Serializable
    data object ScreenList
    @Serializable
    data class ScreenDetail(val id: Int)
}
@Composable
fun PokemonNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationPokemon.ScreenList) {
        composable<DestinationPokemon.ScreenList>{
            ListScreen(
                navigateToScreenDetails = { navController.navigate(DestinationPokemon.ScreenDetail(it)) }
            )
        }
        composable<DestinationPokemon.ScreenDetail> { backStack->
            val id = backStack.toRoute<DestinationPokemon.ScreenDetail>().id
            DetailScreen(
                navigateToScreenList = {navController.navigate((DestinationPokemon.ScreenList))},
                id
            )
        }
    }
}