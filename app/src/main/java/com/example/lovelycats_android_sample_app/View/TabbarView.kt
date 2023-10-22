package com.example.lovelycats_android_sample_app.View

import BreedModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lovelycats_android_sample_app.Helpers.ScreenConfig
import com.squareup.moshi.Moshi

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabbarView(navController: NavHostController,
               popToRoot: () -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.route != ScreenConfig.CatDetail.route) {
                BottomBar(navController = navController)
            }
        }
    ) {
        BottomNavGraph(navController = navController, popToRoot = {
            popToRoot()
        })
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        ScreenConfig.Home,
        ScreenConfig.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: ScreenConfig,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = Color.Black,
        selectedContentColor = Color.White,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun BottomNavGraph(navController: NavHostController,
                   popToRoot: () -> Unit) {
    NavHost(
        navController = navController,
        startDestination = ScreenConfig.Home.route
    ) {
        composable(route = ScreenConfig.Home.route) {
            CatListViewRendering(navigationCallBack = {
                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(BreedModel::class.java).lenient()
                val modelJson = jsonAdapter.toJson(it)
                navController.navigate(ScreenConfig.CatDetail.route.replace("{model}", modelJson))
            }, popToRoot = {
                popToRoot()
            })
        }

        composable(route = ScreenConfig.CatDetail.route
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

        composable(route = ScreenConfig.Settings.route) {
            Text(text = "Settings")
        }
    }
}
