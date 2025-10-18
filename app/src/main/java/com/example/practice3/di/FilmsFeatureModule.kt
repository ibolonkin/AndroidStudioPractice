package com.example.practice3.di

import com.example.practice3.Films
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.data.mapper.FilmsResponseToEntityMapper
import com.example.practice3.news.data.model.FilmsApi
import com.example.practice3.news.data.repository.FilmsRepository
import com.example.practice3.news.domain.interactor.FilmsInteractor
import com.example.practice3.news.presentation.viewModel.FilmsDetailsViewModel
import com.example.practice3.news.presentation.viewModel.FilmsListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val filmsFeatureModule = module {
    viewModel { FilmsDetailsViewModel(get(), get()) }
    viewModel { FilmsListViewModel(get(), get()) }

    single { get<Retrofit>().create(FilmsApi::class.java) }

    factory { FilmsResponseToEntityMapper() }
    single { FilmsRepository(get(), get()) }

    single { FilmsInteractor(get()) }
}