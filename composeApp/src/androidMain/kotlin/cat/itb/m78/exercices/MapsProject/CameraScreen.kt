import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.MapsProject.DrawerMenu
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.compose.runtime.State
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.compose.ui.text.font.FontVariation
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.awaitCancellation
private const val LAST_PHOTO_KEY = "lastPhoto"
class CameraViewModel() : ViewModel() {

    private val _savedPhotoUri = mutableStateOf<Uri?>(null)
    val photo: State<Uri?> = _savedPhotoUri
    val settings: Settings = Settings()
    val lastPhotoView = settings.getString(LAST_PHOTO_KEY, "https://www.computerhope.com/jargon/e/error.png")
    var lastPhoto = mutableStateOf<Uri?>(lastPhotoView.toUri())
    val surferRequest = mutableStateOf<SurfaceRequest?>(null)

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            surferRequest.value = newSurfaceRequest
        }
    }

    val imageCaptureUseCase: ImageCapture = ImageCapture.Builder().build()

    suspend fun bindToCamera(appContext: Context, lifecycleOwner: LifecycleOwner) {
        val processCameraProvider = ProcessCameraProvider.awaitInstance(appContext)
        processCameraProvider.bindToLifecycle(
            lifecycleOwner,
            DEFAULT_BACK_CAMERA,
            cameraPreviewUseCase,
            imageCaptureUseCase
        )
        try {
            awaitCancellation()
        } finally {
            processCameraProvider.unbindAll()
        }
    }

    fun takePhoto(context: Context) {
        val name = "photo_${System.currentTimeMillis()}.jpg"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/Monuments")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }
        val resolver = context.contentResolver
        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        else
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            collection,
            contentValues
        ).build()

        imageCaptureUseCase.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e("CameraViewModel",
                        "Error al tomar foto: ${exc.message}", exc)
                }
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        contentValues.clear()
                        contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                        output.savedUri?.let { resolver.update(it, contentValues, null, null) }
                    }
                    Log.d("CameraViewModel", "Foto guardada: ${output.savedUri}")
                    _savedPhotoUri.value = output.savedUri

                }
            }
        )
    }
    //Agafar l'última foto
    fun changeLastPhoto(){
        lastPhoto.value = photo.value
        settings[LAST_PHOTO_KEY] =  lastPhoto.value.toString()

    }

    init {
        settings[LAST_PHOTO_KEY] =  lastPhoto.value.toString()
    }
}

@Composable
fun CameraScreen(
    navigateBackWithPhoto: (String) -> Unit,
    navigateToScreenMarkers: () -> Unit,
    navigateToScreenMap: () -> Unit
) {
    val backgroundColor = Color(0xFFF1F8E9)
    val cameraBoxBg = Color(0xFFFFF3E0)
    val buttonColor = Color(0xFFB2DFDB)

    DrawerMenu(
        content = { innerPadding ->
            val viewModel = viewModel { CameraViewModel() }
            val context = LocalContext.current
            val lifecycleOwner = LocalLifecycleOwner.current

            LaunchedEffect(Unit) {
                viewModel.bindToCamera(context, lifecycleOwner)
            }

            val surfaceRequest = viewModel.surferRequest.value
            surfaceRequest?.let { request ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(backgroundColor)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .align(Alignment.TopCenter),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Capture Marker Photo",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3f / 4f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(cameraBoxBg)
                        ) {
                            CameraXViewfinder(
                                surfaceRequest = request,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Button(
                            onClick = {

                                viewModel.takePhoto(context)
                                viewModel.changeLastPhoto()
                                viewModel.photo.value?.let {

                                    navigateBackWithPhoto(viewModel.photo.value.toString())
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text("Take Photo")
                        }
                    if(viewModel.lastPhoto.value.toString() != "https://www.computerhope.com/jargon/e/error.png") {
                        Button(
                            onClick = {
                                viewModel.lastPhoto.value?.let {
                                    navigateBackWithPhoto(it.toString())
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text("Select last photo")
                        }
                    }

                    }
                }
            }
        },
        navigateToScreenMap,
        navigateToScreenMarkers
    )
}
