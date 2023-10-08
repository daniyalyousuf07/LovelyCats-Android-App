package com.example.lovelycats_android_sample_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lovelycats_android_sample_app.View.CatDetailView
import com.example.lovelycats_android_sample_app.View.CatListViewRendering
import com.example.lovelycats_android_sample_app.ViewModel.CatDetailViewModel
import com.example.lovelycats_android_sample_app.ui.theme.LovelyCatsAndroidSampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LovelyCatsAndroidSampleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainFlow()
                }
            }
        }
    }
}

@Composable
fun MainFlow() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "cat-listing") {
        composable(route = "cat-listing") {
            CatListViewRendering(navigationCallBack = {
                navigationController.navigate("cat-detail/$it")
            })
        }
        composable(route = "cat-detail/{cat-id}",
            arguments = listOf(navArgument(name = "cat-id") {
                type = NavType.StringType
            })
        ) {
            val catId = it.arguments?.getString("cat-id")
            println(catId)
            
            CatDetailView(id = catId!!)
        }
    }
}

