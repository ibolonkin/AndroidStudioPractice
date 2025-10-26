package com.example.practice3.news.presentation.model

data class FilmsListViewState(
    val listState: State = State.Loading,
    val filters: List<FilmsListFilter> = FilmsListFilter.entries,
    val currentFilter: FilmsListFilter = FilmsListFilter.ALL,
) {
    sealed interface State {
        object Loading : State
        data class Error(val error: String): State
        data class Success(val data: List<FilmsUiModel>) : State
    }
}

enum class FilmsListFilter(val text: String) {
    ALL("Все"),
    FAVORITES("Избранные")
}