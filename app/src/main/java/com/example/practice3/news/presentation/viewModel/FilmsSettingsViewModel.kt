package com.example.practice3.news.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.data.cache.BadgeCache
import com.example.practice3.news.domain.interactor.FilmsInteractor
import com.example.practice3.news.presentation.model.FilmsSettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmsSettingsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: FilmsInteractor,
    private val badgeCache: BadgeCache,
): ViewModel() {
    private val mutableState = MutableStateFlow(FilmsSettingsState())
    val viewState = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            interactor.observeFilmFirstSettings().collect { filmFirst ->
                mutableState.update { it.copy(filmsFirst = filmFirst) }
            }
        }
    }

    fun onFilmsFirstCheckedChange(isChecked: Boolean) {
        mutableState.update { it.copy(filmsFirst = isChecked) }
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            val filmsFirst = viewState.value.filmsFirst
            interactor.setFilmFirstSetting(filmsFirst)

            onBack()
        }
    }

    fun onResetClicked() {
        viewModelScope.launch {
            interactor.setFilmFirstSetting(false)
            badgeCache.setFiltersActive(false)
            mutableState.update { it.copy(filmsFirst = false) }
        }
    }


}