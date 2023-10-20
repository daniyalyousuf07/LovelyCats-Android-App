package com.example.lovelycats_android_sample_app.View

import BreedModel
import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.lovelycats_android_sample_app.ViewModel.CatbreedViewModel
import com.example.lovelycats_android_sample_app.ui.theme.LightGreen
import kotlinx.coroutines.launch
import okhttp3.internal.wait




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListViewRendering(navigationCallBack: (BreedModel) -> Unit) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    val viewModel: CatbreedViewModel = viewModel()
    val breed = viewModel.catListState.value
    var loading = viewModel.isLoading.value!!

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
        ModalDrawerSheet {
//            Row(horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(start = 8.0.dp)) {
//                Icon(Icons.Filled.Home, contentDescription = "Menu")
//                Text("Menu", modifier = Modifier.padding(16.dp))
//            }
//            Divider()
            NavigationDrawerItem(
                label = {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 8.0.dp)) {
                        Icon(Icons.Filled.Home, contentDescription = "Menu")
                        Text("Home", modifier = Modifier.padding(16.dp))
                    }
                },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
            Divider()
            NavigationDrawerItem(
                label = {
                    Row(horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(start = 8.0.dp)) {
                        Icon(Icons.Filled.Close, contentDescription = "Logout")
                        Text("Logout", modifier = Modifier.padding(16.dp))
                    }
                },
                selected = false,
                onClick = {

                }
            )
        }

    }) {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Cats", color = Color.White)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(Icons.Filled.Menu,
                                "backIcon",
                                tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(12.dp),
                            color = MaterialTheme.colorScheme.primary,
                            strokeWidth = 2.dp,
                        )
                    } else {
                        LazyColumn(contentPadding = PaddingValues(16.dp)) {
                            items(breed) {
                                CatListCell(
                                    breed = it
                                    , navigationCallBack)
                            }
                        }
                    }
                }
            })

    }
}
@Composable
fun CatListCell(
    breed: BreedModel,
    navigationCallBack: (BreedModel) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                navigationCallBack(breed)
            }
    )     {
        Row(modifier = Modifier.animateContentSize()) {
            Spacer(modifier = Modifier.padding(5.dp))
            AsyncImage(model = "https://cataas.com/cat/says/hello%20world!",
                contentDescription = breed.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.LightGreen
                        ), shape = CircleShape
                    )
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(0.8f)
                .padding(2.dp),
            ) {
                Text(text = breed.name,
                    style = MaterialTheme.typography.headlineLarge)

                CompositionLocalProvider {
                    Text(text = breed.description,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
