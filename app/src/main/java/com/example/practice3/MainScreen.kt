package com.example.practice3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.scene.DialogSceneStrategy
import androidx.navigation3.ui.NavDisplay
import com.example.practice3.navigation.Route
import com.example.practice3.navigation.TopLevelBackStack
import com.example.practice3.news.presentation.model.FilmsUiModel
import com.example.practice3.news.presentation.screen.EditProfileScreen
import com.example.practice3.news.presentation.screen.FilmsDetailsDialog
import com.example.practice3.news.presentation.screen.FilmsListScreen
import com.example.practice3.news.presentation.screen.FilmsSettingsDialog
import com.example.practice3.news.presentation.screen.ProfileScreen
import org.koin.java.KoinJavaComponent.inject
//import com.google.android.libraries.mapsplatform.transportation.consumer.model.Route

import kotlin.getValue

interface TopLevelRoute: com.example.practice3.navigation.Route{
    val icon: ImageVector
}

data object Films: TopLevelRoute {
    override val icon = Icons.Default.Face
}

data object News: TopLevelRoute {
    override val icon = Icons.AutoMirrored.Filled.List
}

data object FilmsSettings: Route

data class FilmsDetails(val films: FilmsUiModel) : com.example.practice3.navigation.Route

data object Profile : TopLevelRoute {
    override val icon = Icons.Default.Face
}

data object EditProfile : Route

@Composable
fun MainScreen() {
    val topLevelBackStack by inject<TopLevelBackStack<Route>>(clazz = TopLevelBackStack::class.java)
    val dialogStrategy = remember { DialogSceneStrategy<Route>() }

    Scaffold(bottomBar = {
        NavigationBar {
            listOf(Films, News, Profile).forEach { route ->
                NavigationBarItem(
                    icon = { Icon(route.icon, null) },
                    selected = topLevelBackStack.topLevelKey == route,
                    onClick = {
                        topLevelBackStack.addTopLevel(route)
                    }
                )
            }
        }
    }) { padding ->
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            modifier = Modifier.padding(padding),
            sceneStrategy = dialogStrategy,
            entryDecorators = listOf(
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<News> {
                    ContentGray("test")
                }
                entry<Films> {
                    FilmsListScreen()
                }
                entry<FilmsDetails>(
                    metadata = DialogSceneStrategy.dialog(DialogProperties())
                ){
                    FilmsDetailsDialog(it.films)
                }
                entry<FilmsSettings>(
                    metadata = DialogSceneStrategy.dialog(DialogProperties())
                ) {
                    FilmsSettingsDialog()
                }
                entry<Profile> {
                    ProfileScreen()
                }

                entry<EditProfile> {
                    EditProfileScreen()
                }
            }
        )
    }
}

@Composable
fun ContentGray(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    )
}

@Composable
fun ContentGreen(text: String, content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text)
        content()
    }
}