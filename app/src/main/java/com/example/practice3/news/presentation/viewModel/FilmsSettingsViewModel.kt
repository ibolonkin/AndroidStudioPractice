package com.example.practice3.news.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.model.FilmsSettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilmsSettingsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
): ViewModel() {
    private val mutableState = MutableStateFlow(FilmsSettingsState())
    val viewState = mutableState.asStateFlow()

    fun onFilmsFirstCheckedChange(isChecked: Boolean) {
        mutableState.update { it.copy(filmsFirst = isChecked) }
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }

    fun onSaveClicked() {
        onBack()
    }
}