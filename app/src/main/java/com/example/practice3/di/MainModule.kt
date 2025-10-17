package com.example.practice3.di

import com.example.practice3.Films
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.viewModel.FilmsDetailsViewModel
import org.koin.dsl.module
import androidx.appcompat.widget.AppCompatDrawableManager.get
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf

val mainModule = module {
    single { TopLevelBackStack<Route>(Films) }
    //viewModelOf(::FilmsDetailsViewModel)
    viewModel { FilmsDetailsViewModel(get(), get()) }
}