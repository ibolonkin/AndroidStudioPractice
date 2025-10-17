package com.example.practice3.news.presentation.model

data class FilmsDetailViewState(
    val films: FilmsUiModel,
    val rating: Float = 0f,
) {
    val userVoteVisible get() = rating != 0f
}