package com.example.lovelycats_android_sample_app

import BreedModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lovelycats_android_sample_app.View.CatDetailView
import com.example.lovelycats_android_sample_app.View.CatDetailViewConstraintLayout
import com.example.lovelycats_android_sample_app.View.CatListViewRendering
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
                // A surface container using the 'background' color from the theme
               Scaffold(
                   topBar = {
                   TopAppBar(
                       colors =  TopAppBarDefaults.largeTopAppBarColors(),
                       windowInsets = TopAppBarDefaults.windowInsets,
                       title = {
                       Text(text = "List")

                   })
               }) {
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
}

@Composable
fun MainFlow() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = "cat-listing") {
        composable(route = "cat-listing") {
            CatListViewRendering(navigationCallBack = {
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(BreedModel::class.java).lenient()
                val modelJson = jsonAdapter.toJson(it)
                navigationController.navigate("cat-detail={model}".replace("{model}", modelJson))
            })
        }
        composable(route = "cat-detail={model}"
//            ,
//            arguments = listOf(navArgument(name = "cat-id") {
//                type = NavType.StringType
//            })
        ) {
            val modelJson =  it.arguments?.getString("model")
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter(BreedModel::class.java).lenient()
            val model = jsonAdapter.fromJson(modelJson)
            //CatDetailView(model = model!!)
            CatDetailViewConstraintLayout(model = model!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainFlow()
}
