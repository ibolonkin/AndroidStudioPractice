package com.example.practice3.news.domain.interactor

import com.example.practice3.news.data.repository.FilmsRepository

class FilmsInteractor (
    private val filmsRepository: FilmsRepository,
) {
    suspend fun getFilms(filmFirst: Boolean) = filmsRepository.getFilms(filmFirst)

    fun observeFilmFirstSettings() = filmsRepository.observeFilmFirstSettings()

    suspend fun setFilmFirstSetting(filmFirst: Boolean) =
        filmsRepository.setFilmFirstSettings(filmFirst)
}