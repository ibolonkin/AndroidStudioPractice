package com.example.practice3.news.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
class FilmsListResponse(
    val documents: List<FilmsListDocument>?,
)

@Keep
@Serializable
class FilmsListDocument(
    val id: String?,
    val title: String?,
    val descr: String?,
    val year: String?,
    val imageUrl: String?,
)