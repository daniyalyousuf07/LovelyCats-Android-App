package com.example.lovelycats_android_sample_app.ViewModel

import BreedModel
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
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
    var isLoading: MutableState<Boolean> = mutableStateOf<Boolean>(true)
    var catListState = mutableStateListOf<BreedModel>()
    init {
        val scope = CoroutineScope(job + Dispatchers.IO)
        scope.launch {
            val breeds = getBreeds()
            catListState.addAll(breeds)
            isLoading.value = false
        }
    }

    suspend fun getBreeds(): List<BreedModel> {
        return repository.getBreeds()
    }

    fun getBreed(id: String): BreedModel {
        return catListState.first {
            it.id == id
        }
    }

    fun updateFav(index: Int) {
        var isFav = catListState[index].isFav
        catListState[index] = catListState[index].copy(isFav = isFav.not())
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}