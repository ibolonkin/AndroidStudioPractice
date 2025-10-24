package com.example.practice3.news.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.practice3.news.data.mapper.FilmsResponseToEntityMapper
import com.example.practice3.news.data.model.FilmsApi
import com.example.practice3.news.domain.model.FilmsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FilmsRepository(
    private val api: FilmsApi,
    private val mapper: FilmsResponseToEntityMapper,
    private val dataStore: DataStore<Preferences>,
) {
    private val filmFirstKey = booleanPreferencesKey(FILM_FIRST_KEY)

    suspend fun getFilms(filmFirst: Boolean): List<FilmsEntity> = withContext(Dispatchers.IO) {
        val response = api.getFilms()
        val films = mapper.mapResponse(response)

        if (filmFirst) {
            films.sortedByDescending { it.year.toIntOrNull() ?: 0 }
        } else {
            films.sortedBy { it.year.toIntOrNull() ?: 0 }
        }
    }

    suspend fun setFilmFirstSettings(filmFirst: Boolean) = withContext(Dispatchers.IO) {
        dataStore.edit {
            it[filmFirstKey] = filmFirst
        }
    }

    fun observeFilmFirstSettings(): Flow<Boolean> =
        dataStore.data.map { it[filmFirstKey] ?: false }

    companion object {
        private const val FILM_FIRST_KEY = "FILM_FIRST_KEY"
    }
}