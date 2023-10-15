package com.example.lovelycats_android_sample_app.View

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.lovelycats_android_sample_app.R
import com.example.lovelycats_android_sample_app.ScaffoldWithTopBar
import com.example.lovelycats_android_sample_app.ui.theme.LightGreen

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowOnboardingView() {
    val presentMainFlow = mutableStateOf(false)

    var imagesArray = arrayOf(
        R.drawable.cat1,
        R.drawable.cat2,
        R.drawable.cat3
    )
    val pagerState = rememberPagerState(initialPage = 0)
    HorizontalPager(pageCount = imagesArray.size, state = pagerState) { page ->
        // Our page content
        val shape =  RoundedCornerShape(8.dp)
        val height = 100.dp
        Box(
            modifier = Modifier
                //.height(height)
                .fillMaxWidth()
                .background(White, shape = shape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painterResource(imagesArray.get(page)),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )


            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row(
                    Modifier
                        .height(50.dp),
                        horizontalArrangement = Arrangement.Center
                ) {
                    repeat(imagesArray.size) { iteration ->
                        val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color = color)
                                .size(20.dp)
                        )
                    }
                }

                Button(onClick = {
                    presentMainFlow.value = true
                }) {
                    Text(text = "Skip")
                }
            }



            if (presentMainFlow.value) {
                ScaffoldWithTopBar()
               // LoginView()
            }
        }
    }
}

@Preview
@Composable
fun PreviewShowOnboardingView() {
    ShowOnboardingView()
}