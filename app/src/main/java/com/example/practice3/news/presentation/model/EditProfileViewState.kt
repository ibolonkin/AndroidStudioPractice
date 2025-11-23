package com.example.practice3.news.presentation.model

data class EditProfileViewState(
    val name: String = "",
    val photoUri: String = "",
    val resumeUrl: String = "",
    val position: String = "Старший разработчик",
    val favoritePairTime: String = "",
    val showPhotoSourceDialog: Boolean = false,
    val isLoading: Boolean = false,
    val timeError: String? = null
)