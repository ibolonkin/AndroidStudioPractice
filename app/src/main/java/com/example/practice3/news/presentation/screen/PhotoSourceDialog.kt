package com.example.practice3.news.presentation.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.practice3.R

@Composable
fun PhotoSourceDialog(
    onDismiss: () -> Unit,
    onGallerySelected: () -> Unit,
    onCameraSelected: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Выберите источник") },
        text = { Text("Откуда взять фото?") },
        confirmButton = {
            TextButton(onClick = onCameraSelected) {
                Text("Камера")
            }
        },
        dismissButton = {
            TextButton(onClick = onGallerySelected) {
                Text("Галерея")
            }
        }
    )
}