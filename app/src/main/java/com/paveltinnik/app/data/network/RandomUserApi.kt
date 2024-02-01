package com.paveltinnik.app.data.network

import com.paveltinnik.app.domain.entity.PersonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("/api/")
    suspend fun getResults(
        @Query("results")
        results: String = "20",
        @Query("format")
        format: String = "pretty",
        @Query("page")
        pageNumber: Int = 1
    ): Response<PersonsResponse>
}