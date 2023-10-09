package com.example.lovelycats_android_sample_app.ViewModel

import BreedModel
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lovelycats_android_sample_app.Models.CatImageDetailModel
import com.example.lovelycats_android_sample_app.Repository.CatbreedRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class CatbreedViewModel() : ViewModel() {
    private val repository: CatbreedRepository = CatbreedRepository.getInstance()
    private val job = Job()
    var catListState: MutableState<List<BreedModel>> = mutableStateOf(emptyList<BreedModel>())
    init {
        val scope = CoroutineScope(job + Dispatchers.IO)
        scope.launch {
            val breeds = getBreeds()
            catListState.value = breeds
        }
    }

    suspend fun getBreeds(): List<BreedModel> {
        return repository.getBreeds()
    }

    fun getBreed(id: String): BreedModel {
        return catListState.value.first {
            it.id == id
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}