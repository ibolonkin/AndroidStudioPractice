package com.example.practice3.news.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.practice3.FilmsDetails
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.MockData
import com.example.practice3.news.presentation.model.FilmsListViewState
import com.example.practice3.news.presentation.model.FilmsUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilmsListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,

): ViewModel() {
    private val mutableState = MutableStateFlow(FilmsListViewState())
    val viewState = mutableState.asStateFlow()

    init {
        mutableState.update {
            it.copy(
                state = FilmsListViewState.State.Success(MockData.getFilms())
            )
        }
    }

    fun onFilmsClick(films: FilmsUiModel) {
        topLevelBackStack.add(FilmsDetails(films))
    }
}