package com.example.practice3.news.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.domain.interactor.FilmsInteractor
import com.example.practice3.news.domain.model.FilmsEntity
import com.example.practice3.news.presentation.model.FilmsDetailViewState
import com.example.practice3.news.presentation.model.FilmsUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmsDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val films: FilmsUiModel,
    private val interactor: FilmsInteractor,
): ViewModel() {
    private val mutableState = MutableStateFlow(FilmsDetailViewState(films))
    val state = mutableState.asStateFlow()

    fun onRatingChange(rating: Float) {
        mutableState.update { it.copy(rating = rating) }

        if (rating > 4f) {
            viewModelScope.launch {
                interactor.saveFavorites(
                    FilmsEntity(
                        films.id ?: "",
                        films.title,
                        films.descr,
                        films.year,
                        films.imageUrl,
                    )
                )
            }
        }
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }
}