package cat.itb.m78.exercices.EXAMEN

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.Wireframe.DetailScreen
import kotlinx.serialization.Serializable

object DestinationExamen {
    @Serializable
    data object ScreenList
    @Serializable
    data class ScreenFaltas(val id: Int)
}
@Composable
fun ExamenNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DestinationExamen.ScreenList) {
        composable<DestinationExamen.ScreenList>{
            ListScreen(
                navigateToScreenFaltas = { navController.navigate(DestinationExamen.ScreenFaltas(it)) }
            )
        }
        composable<DestinationExamen.ScreenFaltas> { backStack->
            val id = backStack.toRoute<DestinationExamen.ScreenFaltas>().id
            ScreenFaltas(
                id,
                navigateToScreenList = {navController.navigate((DestinationExamen.ScreenList))}

            )
        }
    }
}