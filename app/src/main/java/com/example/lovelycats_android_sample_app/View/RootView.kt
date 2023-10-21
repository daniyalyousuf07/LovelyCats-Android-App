package com.example.lovelycats_android_sample_app.View

import BreedModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lovelycats_android_sample_app.Helpers.ScreenConfig
import com.example.lovelycats_android_sample_app.Managers.PreferencesManager
import com.squareup.moshi.Moshi

@Composable
fun RootView() {
    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val hasSeenOnboarding = remember { mutableStateOf(preferencesManager.get(key = "hasSeenOnboarding",
        false)) }


    val navigationController = rememberNavController()

    var startDestination = if (hasSeenOnboarding.value) ScreenConfig.Home.route else ScreenConfig.Onboarding.route

    NavHost(navController = navigationController, startDestination = startDestination) {
        composable(route = ScreenConfig.Home.route) {
            TabbarView(navController = navigationController)
        }

        composable(route = ScreenConfig.Login.route) {
            LoginView(navController = navigationController)
        }

        composable(route = ScreenConfig.Onboarding.route) {
            ShowOnboardingView(navController = navigationController)
            preferencesManager.save("hasSeenOnboarding", true)
        }
    }
}