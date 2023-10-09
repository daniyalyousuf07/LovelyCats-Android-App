package com.example.lovelycats_android_sample_app.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.lovelycats_android_sample_app.Models.CatImageDetailModel
import com.example.lovelycats_android_sample_app.Repository.CatbreedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CatDetailViewModel(id: String): ViewModel() {
    private val repository: CatbreedRepository = CatbreedRepository.getInstance()
    var imageDetailState: MutableState<List<CatImageDetailModel>> = mutableStateOf(emptyList<CatImageDetailModel>())
    private val job = Job()

    init {
        val scope = CoroutineScope(job + Dispatchers.IO)
//        val catId = savedState.get<String>("cat-id")?: ""
        scope.launch {
            imageDetailState.value = getImageDetail(id = id)
        }
    }

    suspend fun getImageDetail(id: String): List<CatImageDetailModel> {
        val model = repository.getImageDetail(id = id)
        imageDetailState.value = model
        return model
    }
}