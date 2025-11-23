package com.example.practice3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.practice3.news.presentation.model.FilmsDetailViewState
import com.example.practice3.ui.theme.Practice3Theme
import com.example.practice3.utils.createNotificationChannel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(this)

        enableEdgeToEdge()
        setContent {
            Practice3Theme {
                MainScreen()
            }
        }

    }
}