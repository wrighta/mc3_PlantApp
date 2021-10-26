package com.example.plantapp.dataaccess

import com.example.plantapp.data.PlantEntity
import retrofit2.Response
import retrofit2.http.GET

interface PlantApi {

    // I simply get the json file, however you will probably have an API endpoint in here from a proper Rest API
    @GET("plants.json")
    suspend fun getPlants() : List<PlantEntity>
}
