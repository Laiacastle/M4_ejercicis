package cat.itb.m78.exercices.EXAMEN

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import catitbm78exercicisdb.Faltas

@Composable
fun ScreenFaltas(id: Int, navigateToScreenList: () ->Unit){
    val model = viewModel{ FaltasScreenVM(id) }
    ScreenFaltas(model.faltas, navigateToScreenList)
}

@Composable
fun ScreenFaltas(student: List<Faltas>, navigateToScreenList: () -> Unit){
    Column(modifier = Modifier.fillMaxSize().padding(20.dp)){
        LazyColumn(modifier = Modifier.fillMaxWidth()){
            itemsIndexed(student) { _, student ->
                Card(modifier = Modifier.fillMaxWidth().padding(5.dp)){
                    Text("ID: " + student.Id.toString())
                    Text("Data: " + student.Date)
                }

            }}
            Button(onClick = {navigateToScreenList()}){
                Text("<--")
            }
    }
}