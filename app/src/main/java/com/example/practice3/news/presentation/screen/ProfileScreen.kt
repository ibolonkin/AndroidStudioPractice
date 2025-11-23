package com.example.practice3.news.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.example.practice3.R
import com.example.practice3.news.presentation.viewModel.ProfileViewModel
import com.example.practice3.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen() {
    val viewModel = koinViewModel<ProfileViewModel>()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    ProfileContent(
        state = state,
        onEditClick = viewModel::onEditClick,
        onDownloadResume = { viewModel.onDownloadResume(context) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    state: com.example.practice3.news.presentation.model.ProfileViewState,
    onEditClick: () -> Unit,
    onDownloadResume: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Профиль") },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Редактировать",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { onEditClick() }
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = state.photoUri.ifEmpty { null },
                contentDescription = "Аватар",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(128.dp),
                placeholder = painterResource(android.R.drawable.ic_menu_camera),
                error = painterResource(android.R.drawable.ic_menu_camera)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = state.name.ifEmpty { "Имя не указано" }, style = Typography.headlineLarge)
            Text(text = state.position, style = Typography.labelMedium)

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onDownloadResume) {
                Text(text = "Резюме")
            }
        }
    }
}