package com.example.lovelycats_android_sample_app.Helpers

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenConfig(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : ScreenConfig(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Settings : ScreenConfig(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )

    object Login: ScreenConfig (
        route = "login",
        title = "Login",
        icon = Icons.Default.Settings
    )

    object Onboarding: ScreenConfig (
        route = "onboarding",
        title = "Onboarding",
        icon = Icons.Default.Settings
    )

    object CatDetail: ScreenConfig (
        route = "cat-detail={model}",
        title = "Cat Detail",
        icon = Icons.Default.Settings
    )
}