package com.example.practice3.news.data.repository

import com.example.practice3.news.data.mapper.FilmsResponseToEntityMapper
import com.example.practice3.news.data.model.FilmsApi
import com.example.practice3.news.domain.model.FilmsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmsRepository(
    private val api: FilmsApi,
    private val mapper: FilmsResponseToEntityMapper,
) {
    suspend fun getFilms(): List<FilmsEntity> = withContext(Dispatchers.IO) {
        val response = api.getFilms()
        mapper.mapResponse(response)
    }
}