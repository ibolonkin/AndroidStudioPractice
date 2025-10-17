package com.example.practice3.uikit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun FullscreenError(
    retry: () -> Unit,
    text: String? = null,
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(Spacing.medium),
        contentAlignment = Alignment.Center
    ) {
        ErrorItem(
            error = text,
            onClick = retry,
        )
    }
}