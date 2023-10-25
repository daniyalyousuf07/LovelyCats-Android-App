package com.example.lovelycats_android_sample_app.View

import BreedModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lovelycats_android_sample_app.Managers.PreferencesManager
import com.example.lovelycats_android_sample_app.Navigation.NavGraph.setupNavigation
import com.squareup.moshi.Moshi

@Composable
fun RootView() {
    setupNavigation()
}