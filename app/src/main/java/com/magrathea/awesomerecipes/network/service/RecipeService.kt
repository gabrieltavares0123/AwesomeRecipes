package com.magrathea.awesomerecipes.network.service

import com.magrathea.awesomerecipes.network.model.RecipeDto
import com.magrathea.awesomerecipes.network.response.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface RecipeService {

    @GET(value = "search")
    suspend fun search(
        @Header(value = "Authorization") token: String,
        @Query(value = "page") page: Int,
        @Query(value = "query") query: String
    ): RecipeSearchResponse

    @GET(value = "get")
    suspend fun get(
        @Header(value = "Authorization") token: String,
        @Query(value = "id") id: Long
    ): RecipeDto
}