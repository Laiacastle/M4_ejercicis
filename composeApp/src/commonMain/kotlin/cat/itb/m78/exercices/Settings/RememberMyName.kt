package cat.itb.m78.exercices.Settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

private const val NAME_VIEW_KEY = "name"
class RememberNameViewModel : ViewModel(){
    val settings: Settings = Settings()

    val nameViews = settings.getString(NAME_VIEW_KEY, "")
    var name = mutableStateOf(nameViews)
    fun changeName(newName: String){
        name.value =newName
        settings[NAME_VIEW_KEY] =  name.value

    }
    init {
        settings[NAME_VIEW_KEY] =  name.value
    }
}

@Composable
fun RememberMyName() {
    val model = viewModel{RememberNameViewModel()}
    Column {
        Text("Helo, "+ model.nameViews)
        var text by remember{ mutableStateOf("")}

        TextField(value = text,
                    onValueChange = {text = it},
                    label = { Text("Name") }
                    )
        Button(onClick = {model.changeName(text)}){
            Text("Guardar")
        }
} }
