package cat.itb.m78.exercices.Db

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
data class Language(val text: String, val language: String)
class MessagesViewModel : ViewModel() {
    val messagesQueries = database.messagesQueries
    val idiomaQueries = database.idiomaQueries
    var all = messagesQueries.selectAll().executeAsList()
    var allLanguages = idiomaQueries.selectAll().executeAsList()
    fun addMessage(newMessage: String){
        messagesQueries.insert(newMessage)
    }
    fun updateMessages(){
        all = messagesQueries.selectAll().executeAsList()
    }
    fun addLanguage(text: String, language: String){
        val newLanguage = Language(text, language)
        idiomaQueries.insert(newLanguage.text, newLanguage.language)
    }
    fun updateLanguage(){
        allLanguages = idiomaQueries.selectAll().executeAsList()
    }
    fun filtre(language: String){
        allLanguages = idiomaQueries.filter(language).executeAsList()
    }
}

@Composable
fun Screen(){
    val viewModel = viewModel{MessagesViewModel() }
    val messages = viewModel.all
    Column {
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") }
        )
        Button(onClick = { viewModel.addMessage(text); viewModel.updateMessages(); text = "" }) {
            Text("Add")
        }

        LazyColumn(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(messages) { _, message ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Column(
                            modifier = Modifier.padding(end = 15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Text(message.text)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ScreenLanguages(){
    val viewModel = viewModel{MessagesViewModel() }
    val languages = viewModel.allLanguages
    Column {
        var text by remember { mutableStateOf("") }
        var text2 by remember {mutableStateOf("")}

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Text") }
        )
        TextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("Idioma") }
        )

        Button(onClick = { viewModel.addLanguage(text, text2); viewModel.updateLanguage(); text = ""; text2 ="" }) {
            Text("Add")
        }
        Row{
            Button(onClick = {viewModel.filtre("Español")} ){
                Text("Español")
            }
            Button(onClick = {viewModel.filtre("Ingles")} ){
                Text("Ingles")
            }
            Button(onClick = {viewModel.filtre("Castalan")} ){
                Text("Catalan")
            }
        }
        LazyColumn(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(languages) { _, language ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Column(
                            modifier = Modifier.padding(end = 15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Text(language.text)
                            Text(language.language)
                        }

                    }
                }
            }
        }

    }

}
