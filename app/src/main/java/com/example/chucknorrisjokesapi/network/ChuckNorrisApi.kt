package com.example.chucknorrisjokesapi.network

import com.example.chucknorrisjokesapi.data.ChuckNorrisModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {
    @GET("jokes/search")
    suspend fun getRandomJoke(
        @Query("query") query: String,
    ): Response<ChuckNorrisModel>
}