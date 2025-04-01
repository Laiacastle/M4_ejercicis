package cat.itb.m78.exercices.Settings.embassament

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Embassament(
    val dia : String,
    val estaci: String,
    val nivell_absolut: Double,
    val percentatge_volum_embassat: Double,
    val volum_embassat: Double
)

object Destination {
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
}

object MyApi {
    var URL = "https://analisi.transparenciacatalunya.cat/resource/gn9e-3qhr.json"
    private var client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    fun porcentaje(text: String): String{
        return text.replace(" ", "%20")
    }
    suspend fun find(filter: String): List<Embassament> {
        val newURL =
            "$URL?estaci=${porcentaje(filter)}"
        return client.get(newURL).body<List<Embassament>>()
    }
    suspend fun list() = client.get(URL).body<List<Embassament>>()
}

private const val PREFE_VIEW_KEY = "prefe"
class EmbassamentViewModel : ViewModel() {
    val settings: Settings = Settings()
    var embassamentList = mutableStateOf(listOf<Embassament>())
    val PrefeViews = settings.getString(PREFE_VIEW_KEY, "")
    var preferit = mutableStateOf("")
    fun ChangeURL(filter: String){
        viewModelScope.launch(Dispatchers.Default) {
            embassamentList.value = MyApi.find(filter)
        }

    }
    fun changePrefer(newPrefe: String){
        preferit.value = newPrefe
        settings[PREFE_VIEW_KEY] =  preferit.value

    }

    fun findByName(name: String): Int?{
        var j = mutableStateOf(0);
        for(i in embassamentList.value){
            if(i.estaci == name){
                return j.value
            }
            j.value ++
        }
        return null
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            embassamentList.value = MyApi.list()
        }
    }
}
@Composable
fun Embassaments(NavigateToScreen2: () -> Unit) {
    val model = viewModel { EmbassamentViewModel() }
    val embassaments = model.embassamentList
    Screen1(embassaments.value, NavigateToScreen2)
}

@Composable
fun Screen1(embassaments: List<Embassament>, NavigateToScreen2: () -> Unit){
    val model = viewModel { EmbassamentViewModel() }

    LazyColumn (modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
        itemsIndexed(embassaments) { _, embassament ->
            Card(modifier = Modifier.fillMaxWidth()){
                Row{
                    Column{
                        Button(onClick = {model.ChangeURL(embassament.estaci);NavigateToScreen2()}){
                            Text(text = embassament.estaci)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun Screen2(){
    val model = viewModel { EmbassamentViewModel() }
    val embassaments = model.embassamentList.value
    LazyColumn (modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
        itemsIndexed(embassaments) { _, embassament ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row {
                    Column {
                        Text(text = embassament.dia)
                        Text(text = embassament.estaci)
                        Text(text = embassament.nivell_absolut.toString())
                        Text(text = embassament.volum_embassat.toString())
                        Text(text = embassament.percentatge_volum_embassat.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun EmbassamentNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Screen1) {
        composable<Destination.Screen1>{
            Embassaments(
                NavigateToScreen2 = { navController.navigate(Destination.Screen2) }
            )
        }
        composable<Destination.Screen2> {
                Screen2()
        }
    }
}