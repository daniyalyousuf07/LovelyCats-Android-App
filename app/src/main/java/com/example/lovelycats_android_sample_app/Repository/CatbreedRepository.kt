package com.example.lovelycats_android_sample_app.Repository

import BreedModel
import com.example.lovelycats_android_sample_app.Models.CatImageDetailModel
import com.example.lovelycats_android_sample_app.Webservice.CatbreedWebService

class CatbreedRepository(private val webService: CatbreedWebService = CatbreedWebService()) {

    suspend fun getBreeds(): List<BreedModel> {
        return webService.getBreeds()
    }

    suspend fun getImageDetail(id: String): List<CatImageDetailModel> {
        return webService.getImageDetail(id = id)
    }

    companion object {
        @Volatile
        private var instance: CatbreedRepository? = null

        fun getInstance() = Companion.instance?: synchronized(this) {
            instance?: CatbreedRepository().also { instance = it }
        }
    }
}