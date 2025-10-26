package com.example.practice3.news.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practice3.FilmsDetails
import com.example.practice3.FilmsSettings
import com.example.practice3.core.launchLoadingAndError
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.data.cache.BadgeCache
import com.example.practice3.news.domain.interactor.FilmsInteractor
import com.example.practice3.news.domain.model.FilmsEntity
import com.example.practice3.news.presentation.MockData
import com.example.practice3.news.presentation.model.FilmsListFilter
import com.example.practice3.news.presentation.model.FilmsListViewState
import com.example.practice3.news.presentation.model.FilmsUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.String

class FilmsListViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: FilmsInteractor,
    private val badgeCache: BadgeCache,
): ViewModel() {
    private val mutableState = MutableStateFlow(FilmsListViewState())
    val viewState = mutableState.asStateFlow()

    private val _showBadge = MutableStateFlow(false)
    val showBadge = _showBadge.asStateFlow()

    init {
        mutableState.update {
            it.copy(
                listState = FilmsListViewState.State.Success(MockData.getFilms())
            )
        }
        loadFilms()

        viewModelScope.launch {
            interactor.observeFilmFirstSettings().collect { filmFirst ->
                val shouldShowBadge = filmFirst
                _showBadge.value = shouldShowBadge
            }
        }
    }




    fun onFilmsClick(films: FilmsUiModel) {
        topLevelBackStack.add(FilmsDetails(films))
    }

    fun onRetryClick() = loadFilms()

    fun onSettingsClick() = topLevelBackStack.add(FilmsSettings)

    fun onFilterChange(filter: FilmsListFilter) {
        mutableState.update { it.copy(currentFilter = filter) }
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launchLoadingAndError(
            handleError = { e -> updateState(FilmsListViewState.State.Error(e.localizedMessage.orEmpty())) }
        ) {
            updateState(FilmsListViewState.State.Loading)

            interactor.observeFilmFirstSettings()
                .onEach { updateState(FilmsListViewState.State.Loading) }
                .map {
                    if (viewState.value.currentFilter == FilmsListFilter.ALL){
                        interactor.getFilms(it)
                    } else {
                        interactor.getFavorites()
                    }
                }
                .collect{ films ->
                    updateState(FilmsListViewState.State.Success(mapToUi(films)))
                }
        }
    }

    private fun updateState(state: FilmsListViewState.State) =
        mutableState.update { it.copy(listState = state) }

    private fun mapToUi(films: List<FilmsEntity>): List<FilmsUiModel> = films.map { films ->
        FilmsUiModel(
            id = films.id,
            title = films.title,
            descr = films.descr,
            year = films.year,
            imageUrl = films.imageUrl,
        )
    }
}
