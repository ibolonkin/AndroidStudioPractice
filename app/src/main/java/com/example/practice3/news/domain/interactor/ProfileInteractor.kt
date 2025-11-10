package com.example.practice3.news.domain.interactor

import com.example.practice3.news.data.repository.ProfileRepository
import com.example.practice3.news.domain.model.Profile
import kotlinx.coroutines.flow.Flow

class ProfileInteractor(
    private val profileRepository: ProfileRepository
) {
    suspend fun saveProfile(profile: Profile) = profileRepository.saveProfile(profile)

    fun getProfile(): Flow<Profile> = profileRepository.getProfile()
}