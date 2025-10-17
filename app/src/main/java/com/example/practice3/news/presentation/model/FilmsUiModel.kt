package com.example.practice3.news.presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmsUiModel (
    val id: Int,
    val title: String,
    val descr: String?,
    val year: String,
    val imageUrl: String?,
)
