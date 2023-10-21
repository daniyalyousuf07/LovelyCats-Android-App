package com.example.lovelycats_android_sample_app

import BreedModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lovelycats_android_sample_app.View.CatDetailView
import com.example.lovelycats_android_sample_app.View.CatDetailViewConstraintLayout
import com.example.lovelycats_android_sample_app.View.CatListViewRendering
import com.example.lovelycats_android_sample_app.View.LoginView
import com.example.lovelycats_android_sample_app.View.RootView
import com.example.lovelycats_android_sample_app.View.ShowOnboardingView
import com.example.lovelycats_android_sample_app.ViewModel.CatDetailViewModel
import com.example.lovelycats_android_sample_app.ui.theme.LovelyCatsAndroidSampleAppTheme
import com.squareup.moshi.Moshi

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LovelyCatsAndroidSampleAppTheme {
                // A surface container using the 'background' color from the themeKey
                RootView()

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RootView()
}
