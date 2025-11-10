package com.example.practice3.news.presentation.screen

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.practice3.news.presentation.viewModel.EditProfileViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditProfileScreen() {
    val viewModel = koinViewModel<EditProfileViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    var cameraUri by remember { mutableStateOf<Uri?>(null) }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            viewModel.showPhotoSourceDialog()
        }
    }

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.updatePhotoFromGallery(uri)
    }

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            cameraUri?.let { uri ->
                viewModel.updatePhotoFromCamera(uri.toString())
            }
        }
    }

    EditProfileContent(
        state = state,
        onPhotoClick = {
            // Проверяем разрешение перед показом диалога
            if (hasCameraPermission(context)) {
                viewModel.showPhotoSourceDialog()
            } else {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        },
        onNameChange = viewModel::onNameChange,
        onResumeUrlChange = viewModel::onResumeUrlChange,
        onSaveClick = {
            viewModel.onSaveClick()
        },
        onBackClick = viewModel::onBackClick,
        onGallerySelected = {
            pickImageLauncher.launch("image/*")
        },
        onCameraSelected = {
            val uri = createImageUri(context)
            if (uri != null) {
                cameraUri = uri
                takePictureLauncher.launch(uri)
            }
        },
        onHidePhotoDialog = viewModel::hidePhotoSourceDialog
    )
}

// Функция проверки разрешения камеры
private fun hasCameraPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

// Функция создания URI для фото
private fun createImageUri(context: Context): Uri? {
    return try {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = context.getExternalFilesDir("profile_photos")
        val imageFile = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            imageFile
        )
    } catch (e: Exception) {
        null
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    state: com.example.practice3.news.presentation.model.EditProfileViewState,
    onPhotoClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onResumeUrlChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    onGallerySelected: () -> Unit,
    onCameraSelected: () -> Unit,
    onHidePhotoDialog: () -> Unit
) {
    // Показываем диалог выбора источника фото
    if (state.showPhotoSourceDialog) {
        PhotoSourceDialog(
            onDismiss = onHidePhotoDialog,
            onGallerySelected = onGallerySelected,
            onCameraSelected = onCameraSelected
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Редактирование профиля") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Аватар
            AsyncImage(
                model = state.photoUri.ifEmpty { null },
                contentDescription = "Аватар",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp)
                    .clickable { onPhotoClick() }
                    .align(Alignment.CenterHorizontally),
                placeholder = painterResource(android.R.drawable.ic_menu_camera),
                error = painterResource(android.R.drawable.ic_menu_camera)
            )

            Text(
                text = "Нажмите на фото для изменения",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            // Поле имени
            OutlinedTextField(
                value = state.name,
                onValueChange = onNameChange,
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth()
            )

            // Поле ссылки на резюме
            OutlinedTextField(
                value = state.resumeUrl,
                onValueChange = onResumeUrlChange,
                label = { Text("Ссылка на резюме") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Кнопка сохранения
            Button(
                onClick = onSaveClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Сохранить")
            }

            // Кнопка назад
            Button(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Назад")
            }
        }
    }
}