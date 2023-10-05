package com.example.lovelycats_android_sample_app.Repository

import BreedModel
import com.example.lovelycats_android_sample_app.Webservice.CatbreedWebService

class CatbreedRepository(private val webService: CatbreedWebService = CatbreedWebService()) {

    suspend fun getBreeds(): List<BreedModel> {
        return webService.getBreeds()
    }


}