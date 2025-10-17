package com.example.practice3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.practice3.news.presentation.model.FilmsDetailViewState
import com.example.practice3.ui.theme.Practice3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practice3Theme {
                MainScreen()
            }
        }

    }
}