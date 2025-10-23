package com.example.practice3.news.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.practice3.Films
import com.example.practice3.FilmsDetails
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.MockData
import com.example.practice3.news.presentation.model.FilmsListViewState
import com.example.practice3.news.presentation.model.FilmsUiModel
import com.example.practice3.news.presentation.viewModel.FilmsListViewModel
import com.example.practice3.uikit.FullscreenError
import com.example.practice3.uikit.FullscreenLoading
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmsListScreen() {
    val viewModel = koinViewModel<FilmsListViewModel>()
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    FilmsListScreenContent(
        state.state,
        viewModel::onFilmsClick,
        viewModel::onRetryClick,
        viewModel::onSettingsClick,
    )
}

@Composable
private fun FilmsListScreenContent(
    state: FilmsListViewState.State,
    onFilmsClick: (FilmsUiModel) -> Unit = {},
    onRetryClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick =  { onSettingsClick() }) {
                Icon(Icons.Default.Settings, "SettingsBtn")
            }
        },
        contentWindowInsets = WindowInsets(0.dp)
    ) {
        Box(Modifier.padding(it)){
            when (state) {
                FilmsListViewState.State.Loading -> {
                    FullscreenLoading()
                }

                is FilmsListViewState.State.Error -> {
                    FullscreenError(
                        retry = { onRetryClick() },
                        text = state.error
                    )
                }

                is FilmsListViewState.State.Success -> {
                    LazyColumn {
                        state.data.forEach { films ->
                            item {
                                FilmsListItem(films) { onFilmsClick(it) }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FilmsListItem(films: FilmsUiModel, onFilmsClick: (FilmsUiModel) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onFilmsClick(films) }
            .padding(horizontal = 16.dp)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = films.title,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "(${films.year})",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        if (!films.descr.isNullOrBlank()) {
            Text(
                text = "Описание: ${films.descr}",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun FilmsListPreview() {
    FilmsListScreen()
}