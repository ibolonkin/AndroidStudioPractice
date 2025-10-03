package com.example.practice3.news.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practice3.Films
import com.example.practice3.FilmsDetails
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.MockData
import com.example.practice3.news.presentation.model.FilmsUiModel

@Composable
fun FilmsListScreen(topLevelBackStack: TopLevelBackStack<Route>) {
    val films = remember { MockData.getFilms() }

    LazyColumn(
        modifier = Modifier.padding(top = 12.dp)
    ) {
        films.forEach { films ->
            item(key = films.id) {
                FilmsListItem(films, {topLevelBackStack.add(FilmsDetails(it))})
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
    FilmsListScreen(TopLevelBackStack<Route>(Films))
}