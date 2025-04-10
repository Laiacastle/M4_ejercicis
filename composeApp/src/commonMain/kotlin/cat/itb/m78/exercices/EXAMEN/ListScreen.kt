package cat.itb.m78.exercices.EXAMEN

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Wireframe.ListVM
import coil3.compose.AsyncImage

@Composable
fun ListScreen(navigateToScreenFaltas: (Int) -> Unit){
    val model = viewModel{ ListScreenVM() }
    ListScreen(model.studentsList.value, navigateToScreenFaltas, model::addFalta)
}
@Composable
fun ListScreen(students: List<Student>?, navigateToScreenFaltas: (Int) -> Unit, addFalta: (Int)->Unit) {
    Column(modifier = Modifier.fillMaxWidth()){
        if (students == null){
            CircularProgressIndicator()
        }else{
            LazyColumn(
                modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                itemsIndexed(students.toList()) { _, student ->


                    Column{
                        Card(modifier = Modifier.fillMaxWidth().clickable(onClick = {navigateToScreenFaltas(student.id)})){
                            Column(modifier = Modifier.fillMaxSize()){

                                Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)){
                                    Text(student.name + " ")
                                    Text(student.surname)
                                }
                                Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                                    Text(student.email)
                                }

                                AsyncImage(
                                    model = student.img,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxWidth()
                                )

                            }
                        }

                        Button(onClick = {addFalta(student.id)}, modifier = Modifier.fillMaxWidth()){
                            Text("Afegir una falta")
                        }

                    }

                }
        }


    }

}
}