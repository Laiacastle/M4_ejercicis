package cat.itb.m78.exercices


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cat.itb.m78.exercices.Camera.CameraNavigation

import cat.itb.m78.exercices.Camera.CameraScreen
import cat.itb.m78.exercices.Maps.MapsScreen
import cat.itb.m78.exercices.MapsProject.MapNavigation

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MapNavigation() }
    }
}

@Preview
@Composable
fun AppPreview() { MapNavigation() }
