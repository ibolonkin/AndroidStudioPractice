package com.example.practice3.news.presentation.viewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.domain.interactor.ProfileInteractor
import com.example.practice3.news.domain.model.Profile
import com.example.practice3.news.presentation.model.EditProfileViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditProfileViewModel(
    private val interactor: ProfileInteractor,
    private val topLevelBackStack: TopLevelBackStack<Route>
) : ViewModel() {
    private val _state = MutableStateFlow(EditProfileViewState())
    val state = _state.asStateFlow()

    private var isSaving = false

    private var currentPhotoUri: Uri? = null

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            val profile = interactor.getProfile().first()
            _state.value = EditProfileViewState(
                name = profile.name,
                photoUri = profile.photoUri,
                resumeUrl = profile.resumeUrl,
                position = profile.position,
                favoritePairTime = profile.favoritePairTime
            )
        }
    }

    fun reloadProfile() {
        viewModelScope.launch {
            interactor.getProfile().collect { profile ->
                _state.value = EditProfileViewState(
                    name = profile.name,
                    photoUri = profile.photoUri,
                    resumeUrl = profile.resumeUrl,
                    position = profile.position,
                    favoritePairTime = profile.favoritePairTime
                )
            }
        }
    }

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onResumeUrlChange(url: String) {
        _state.value = _state.value.copy(resumeUrl = url)
    }

    fun onFavoritePairTimeChange(time: String) {
        _state.value = _state.value.copy(favoritePairTime = time)
        validateTime(time)
    }

    private fun validateTime(time: String) {
        val timeError = if (time.isNotEmpty() && !isValidTimeFormat(time)) {
            "Некорректный формат времени"
        } else null
        _state.value = _state.value.copy(timeError = timeError)
    }

    private fun isValidTimeFormat(time: String): Boolean {
        return try {
            val parts = time.split(":")
            if (parts.size != 2) return false
            val hour = parts[0].toInt()
            val minute = parts[1].toInt()
            hour in 0..23 && minute in 0..59
        } catch (e: Exception) {
            false
        }
    }

    fun isSaveEnabled(): Boolean {
        val currentState = _state.value
        return currentState.name.isNotBlank() &&
                currentState.timeError == null &&
                (currentState.favoritePairTime.isEmpty() || isValidTimeFormat(currentState.favoritePairTime))
    }


    fun showPhotoSourceDialog() {
        _state.value = _state.value.copy(showPhotoSourceDialog = true)
    }

    fun onPhotoClick(context: Context) {
        _state.value = _state.value.copy(showPhotoSourceDialog = true)
    }

    fun updatePhotoFromGallery(uri: Uri?) {
        uri?.let {
            _state.value = _state.value.copy(photoUri = uri.toString())
        }
    }

    fun updatePhotoFromCamera(photoUri: String) {
        _state.value = _state.value.copy(photoUri = photoUri)
    }

    fun onSaveClick(context: Context) {
        GlobalScope.launch {
            try {
                val currentState = _state.value

                interactor.saveProfile(
                    Profile(
                        name = currentState.name,
                        photoUri = currentState.photoUri,
                        resumeUrl = currentState.resumeUrl,
                        position = currentState.position,
                        favoritePairTime = currentState.favoritePairTime
                    )
                )

                if (currentState.favoritePairTime.isNotEmpty() && isValidTimeFormat(currentState.favoritePairTime)) {
                    withContext(Dispatchers.Main) {
                        NotificationScheduler(context).scheduleNotification(
                            currentState.name,
                            currentState.favoritePairTime
                        )
                    }
                }

                withContext(Dispatchers.Main) {
                    topLevelBackStack.removeLast()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onBackClick() {
        topLevelBackStack.removeLast()
    }

    fun hidePhotoSourceDialog() {
        _state.value = _state.value.copy(showPhotoSourceDialog = false)
    }
}