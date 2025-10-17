package com.example.practice3.news.presentation.screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.practice3.FilmsDetails
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.MockData
import com.example.practice3.news.presentation.model.FilmsDetailViewState
import com.example.practice3.news.presentation.model.FilmsUiModel
import com.example.practice3.news.presentation.viewModel.FilmsDetailsViewModel
import com.example.practice3.uikit.RatingBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsDetailsDialog(
    films: FilmsUiModel,
) {
    val viewModel = koinViewModel<FilmsDetailsViewModel>(){
        parametersOf(films)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    ModalBottomSheet(
        onDismissRequest = { viewModel.onBack() },
    ) {
        FilmsDetailsContent(state, viewModel::onRatingChange)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmsDetailsContent(
    state: FilmsDetailViewState,
    onRatingChange: (Float) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val context = LocalContext.current

        Row {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = state.films.title,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = "(${state.films.year})",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Icon(
                Icons.Default.Share,
                contentDescription = null,
                Modifier
                    .clickable{ shareText(context, "Фильм <${state.films.title}>") }
                    .size(26.dp)
            )
        }

        if (!state.films.imageUrl.isNullOrBlank()) {
            GlideImage(
                model = state.films.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )
        }

        if (!state.films.descr.isNullOrBlank()) {
            Text(
                text = state.films.descr,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        RatingBar(state.rating) { onRatingChange(it) }

        if (state.userVoteVisible) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Ваша оценка: ${state.rating}",
                textAlign = TextAlign.Center
            )
        }
    }
}

fun shareText(context: Context, text: String){
    val intent = Intent(Intent.ACTION_SEND). apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(intent, "Поделиться через"))
}

@Preview(showBackground = true)
@Composable
fun FilmsDetailDialogPreview() {
    FilmsDetailsContent(
        FilmsDetailViewState(MockData.getFilms().first()),
    )
}