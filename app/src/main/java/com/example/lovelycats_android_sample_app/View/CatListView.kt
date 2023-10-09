package com.example.lovelycats_android_sample_app.View

import BreedModel
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.lovelycats_android_sample_app.ViewModel.CatbreedViewModel

@Composable
fun CatListViewRendering(navigationCallBack: (BreedModel) -> Unit) {

    val viewModel: CatbreedViewModel = viewModel()
    val breed = viewModel.catListState.value

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(breed) {
            CatListCell(
                breed = it
            , navigationCallBack)
        }
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
            AsyncImage(model = "https://cataas.com/cat/says/hello%20world!",
                contentDescription = breed.name,
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.FillBounds
            )
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
