package com.example.practice3.news.presentation.model

data class FilmsListViewState(
    val state: State = State.Loading,
) {
    sealed interface State {
        object Loading : State
        data class Error(val error: String): State
        data class Success(val data: List<FilmsUiModel>) : State
    }
}