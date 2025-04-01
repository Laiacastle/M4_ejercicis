package cat.itb.m78.exercices.Settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

data class Jokes (val id: Int, val type: String, val setup: String , val punchline: String){fun TString(): String = this.setup + "\n" + this.punchline}


object MyApi{
    private val client = HttpClient(){
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun list() = client.get("https://api.sampleapis.com/jokes/goodJokes").body<List<Jokes?>>()
}
class MyViewModel() : ViewModel(){
    var data = mutableStateOf<Jokes?>(null)
    init{
        viewModelScope.launch(Dispatchers.Default){
            data.value = MyApi.list().random()
        }
    }
}

@Composable
fun Jokes(){
    val model = viewModel{MyViewModel()}
    if(model.data.value == null){
        Text("loading...")
    }else{
        Text(model.data.value!!.TString())
    }

}