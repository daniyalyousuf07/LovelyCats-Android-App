package com.example.lovelycats_android_sample_app.View

import BreedModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.lovelycats_android_sample_app.Models.CatImageDetailModel
import com.example.lovelycats_android_sample_app.ViewModel.CatDetailViewModel
import com.example.lovelycats_android_sample_app.ViewModel.CatbreedViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun CatDetailView(model: BreedModel) {

    val viewModel: CatDetailViewModel = CatDetailViewModel(id = model.id)

    LazyColumn() {
        items(1) {
            AsyncImage(model = viewModel.imageDetailState.value.first().url,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize())
            
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 15.dp)) {
                Text(text = model.name,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium)
                Text(text = model.description,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp)
            }
            

        }
    }
}
