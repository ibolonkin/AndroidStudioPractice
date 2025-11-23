package com.example.practice3.news.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.practice3.news.domain.model.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepository(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val NAME = stringPreferencesKey("profile_name")
        val PHOTO_URI = stringPreferencesKey("profile_photo_uri")
        val RESUME_URL = stringPreferencesKey("profile_resume_url")
        val POSITION = stringPreferencesKey("profile_position")
    }

    suspend fun saveProfile(profile: Profile) {
        dataStore.edit { preferences ->
            preferences[Keys.NAME] = profile.name
            preferences[Keys.PHOTO_URI] = profile.photoUri
            preferences[Keys.RESUME_URL] = profile.resumeUrl
            preferences[Keys.POSITION] = profile.position
        }
    }

    fun getProfile(): Flow<Profile> = dataStore.data.map { preferences ->
        Profile(
            name = preferences[Keys.NAME] ?: "",
            photoUri = preferences[Keys.PHOTO_URI] ?: "",
            resumeUrl = preferences[Keys.RESUME_URL] ?: "",
            position = preferences[Keys.POSITION] ?: "Старший разработчик"
        )
    }
}