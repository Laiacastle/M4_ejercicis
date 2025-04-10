package cat.itb.m78.exercices.Wireframe

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    @Serializable
    data object ScreenFavorites
}

@Composable
fun PokemonNav(){
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        NavigationBar{
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate(DestinationPokemon.ScreenList)},
                icon = {Icon(imageVector = Icons.Default.Home, contentDescription = null)},
                label = {Text("Home")}
            )
            NavigationBarItem(
                selected = false,
                onClick = {navController.navigate((DestinationPokemon.ScreenFavorites))},
                icon = {Icon(imageVector = Icons.Default.Star, contentDescription = null)},
                label = {Text("Favorites pokemons")}
            )
        }
    }){
        NavHost(navController = navController, startDestination = DestinationPokemon.ScreenList) {
            composable<DestinationPokemon.ScreenList>{
                ListScreen(
                    navigateToScreenDetails = { navController.navigate(DestinationPokemon.ScreenDetail(it)) }
                )
            }
            composable<DestinationPokemon.ScreenDetail> { backStack->
                val id = backStack.toRoute<DestinationPokemon.ScreenDetail>().id
                DetailScreen(
                    navigateToScreenList = {navController.navigate(DestinationPokemon.ScreenList)},
                    id
                )
            }
            composable<DestinationPokemon.ScreenFavorites>{
                ScreenFavorites(
                    navigateToScreenList = {navController.navigate(DestinationPokemon.ScreenList)},
                    navigateToScreendetails = {navController.navigate(DestinationPokemon.ScreenDetail(it))}
                )
            }
        }
    }
}