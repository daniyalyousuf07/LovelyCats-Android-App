package com.example.lovelycats_android_sample_app.Navigation.NavGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lovelycats_android_sample_app.Managers.PreferencesManager
import com.example.lovelycats_android_sample_app.View.LoginView
import com.example.lovelycats_android_sample_app.View.ShowOnboardingView

fun NavGraphBuilder.authNavGraph(navController: NavHostController,
                                 preferencesManager: PreferencesManager
) {
    navigation(
        startDestination = AuthScreen.Onboarding.route,
        route = Graph.AUTHENTICATION
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginView(navController = navController)
        }

        composable(route = AuthScreen.Onboarding.route) {
            ShowOnboardingView(navController = navController, didTapSkip = {
                preferencesManager.save("hasSeenOnboarding", true)
            })
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Onboarding : AuthScreen(route = "ONBOARDING")
    object Login : AuthScreen(route = "LOGIN")
}