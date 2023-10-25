package com.example.lovelycats_android_sample_app.Navigation.NavGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.lovelycats_android_sample_app.Managers.PreferencesManager
import homeNavGraph

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val HOME = "home_graph"
}
@Composable
fun setupNavigation(navigationController: NavHostController = rememberNavController()) {

    val context = LocalContext.current
    val preferencesManager = remember { PreferencesManager(context) }
    val hasSeenOnboarding = remember { mutableStateOf(preferencesManager.get(key = "hasSeenOnboarding",
        false)) }
    var ROUTE = if (hasSeenOnboarding.value) Graph.HOME else Graph.AUTHENTICATION

    NavHost(navController = navigationController,
        startDestination = ROUTE,
        route = Graph.ROOT) {
        homeNavGraph(navController = navigationController,
            preferencesManager = preferencesManager)
        authNavGraph(navController = navigationController,
            preferencesManager = preferencesManager)
    }
    
}