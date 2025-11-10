package com.example.practice3.news.presentation.viewModel

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice3.EditProfile
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.domain.interactor.ProfileInteractor
import com.example.practice3.news.presentation.model.ProfileViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val interactor: ProfileInteractor,
    private val topLevelBackStack: TopLevelBackStack<Route>
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileViewState())
    val state = _state.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            interactor.getProfile().collect { profile ->
                _state.value = ProfileViewState(
                    name = profile.name,
                    photoUri = profile.photoUri,
                    resumeUrl = profile.resumeUrl,
                    position = profile.position
                )
            }
        }
    }

    fun onEditClick() {
        topLevelBackStack.add(EditProfile)
    }

    fun onDownloadResume(context: Context) {
        val resumeUrl = _state.value.resumeUrl
        if (resumeUrl.isNotEmpty()) {
            downloadFile(context, resumeUrl, "resume.pdf")
        }
    }

    private fun downloadFile(context: Context, url: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle("Резюме")
            .setDescription("Скачивание резюме")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}