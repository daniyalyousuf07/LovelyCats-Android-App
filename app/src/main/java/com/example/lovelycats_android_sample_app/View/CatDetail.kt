package com.example.lovelycats_android_sample_app.View

import BreedModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lovelycats_android_sample_app.Models.CatImageDetailModel
import com.example.lovelycats_android_sample_app.ReusableComponents.Views.shimmerBrush
import com.example.lovelycats_android_sample_app.ViewModel.CatDetailViewModel
import com.example.lovelycats_android_sample_app.ViewModel.CatbreedViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun CatDetailView(model: BreedModel) {

    val viewModel: CatDetailViewModel = CatDetailViewModel(id = model.id!!)

    LazyColumn() {
        items(1) {

            val matrix = ColorMatrix()
            matrix.setToSaturation(2F)

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(viewModel.imageDetailState.value.first().url)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(400.dp),
                colorFilter = ColorFilter.colorMatrix(matrix)
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp)) {
                Text(text = model.name ?: "",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium)
                Text(text = model.description ?: "",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp)
            }
            

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailViewConstraintLayout(model: BreedModel) {

    val viewModel: CatDetailViewModel = CatDetailViewModel(id = model.id!!)
    val showShimmer = remember { mutableStateOf(true) }

    ConstraintLayout {

        val (image, name, description) = createRefs()
        val matrix = ColorMatrix()
        matrix.setToSaturation(2F)

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.lexica.art/full_jpg/5ff6c2c0-a3da-4dd7-8071-919f66b18fa9")
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(shimmerBrush(targetValue = 1300f, showShimmer = showShimmer.value))
                .fillMaxSize()
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.value(400.dp)
                }
                .height(400.dp),
            colorFilter = ColorFilter.colorMatrix(matrix),
            onSuccess = {
                showShimmer.value = false
            }
        )

        Text(text = model.name ?: "",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
            modifier =
            Modifier.constrainAs(name) {
                top.linkTo(image.bottom, margin = 20.dp)
                centerHorizontallyTo(parent)
            },
        )

        Text(text = model.description ?: "",
            textAlign = TextAlign.Center,
            fontSize = 20.sp, modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(name.bottom, margin = 10.dp)
                }
                .padding(8.dp))
    }
}

