package com.example.lovelycats_android_sample_app.Webservice

import BreedModel
import com.example.lovelycats_android_sample_app.Models.CatImageDetailModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.gson.GsonConverterFactory.*
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class CatbreedWebService {

    private lateinit var api: CatbreedApi

    init {
        var retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(CatbreedApi::class.java)
    }

    interface CatbreedApi {
        @GET("breeds")
        suspend fun getBreeds(): List<BreedModel>

        @GET("images/search")
        suspend fun getImageDetail(@Query ("breed_ids") id: String): List<CatImageDetailModel>
    }

    suspend fun getBreeds(): List<BreedModel> {
        return api.getBreeds()
    }

    suspend fun getImageDetail(@Query ("breed_ids") id: String): List<CatImageDetailModel> {
        return api.getImageDetail(id = id)
    }

}