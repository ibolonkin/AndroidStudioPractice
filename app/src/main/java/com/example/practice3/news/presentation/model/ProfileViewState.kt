package com.example.practice3.news.presentation.model

data class ProfileViewState(
    val name: String = "",
    val photoUri: String = "",
    val resumeUrl: String = "",
    val position: String = "Старший разработчик",
    val isLoading: Boolean = false,
    val error: String? = null
)