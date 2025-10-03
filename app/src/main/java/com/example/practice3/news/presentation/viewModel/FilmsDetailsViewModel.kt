package com.example.practice3.news.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.MockData
import com.example.practice3.news.presentation.model.FilmsDetailViewState
import com.example.practice3.news.presentation.model.FilmsUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilmsDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val films: FilmsUiModel,
): ViewModel() {
    private val mutableState = MutableStateFlow(FilmsDetailViewState(films))
    val state = mutableState.asStateFlow()

    fun onRatingChange(rating: Float) {
        mutableState.update { it.copy(rating = rating) }
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }
}