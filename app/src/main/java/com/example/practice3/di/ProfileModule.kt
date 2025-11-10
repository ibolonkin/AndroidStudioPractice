package com.example.practice3.di

import com.example.practice3.news.data.repository.ProfileRepository
import com.example.practice3.news.domain.interactor.ProfileInteractor
import com.example.practice3.news.presentation.viewModel.EditProfileViewModel
import com.example.practice3.news.presentation.viewModel.ProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileModule = module {
    singleOf(::ProfileRepository)
    singleOf(::ProfileInteractor)

    single { ProfileViewModel(get(), get()) }
    single { EditProfileViewModel(get(), get()) }
}