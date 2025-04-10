package cat.itb.m78.exercices


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.m78.exercices.Db.Screen
import cat.itb.m78.exercices.Db.ScreenLanguages
import cat.itb.m78.exercices.EXAMEN.ExamenNav
import cat.itb.m78.exercices.Settings.Count
import cat.itb.m78.exercices.Settings.Countries
import cat.itb.m78.exercices.Settings.embassament.EmbassamentNav
import cat.itb.m78.exercices.Settings.Jokes
import cat.itb.m78.exercices.Settings.RememberMyName
import cat.itb.m78.exercices.Wireframe.ListScreen
import cat.itb.m78.exercices.Wireframe.PokemonNav
import cat.itb.m78.exercices.theme.AppTheme
import org.jetbrains.compose.reload.DevelopmentEntryPoint

@Composable
internal fun App() = AppTheme {
    ExamenNav()

}
