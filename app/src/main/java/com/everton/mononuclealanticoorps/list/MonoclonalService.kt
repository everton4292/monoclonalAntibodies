package com.everton.mononuclealanticoorps.list

import com.everton.mononuclealanticoorps.model.Monoclonal
import retrofit2.http.GET
import retrofit2.http.Query

interface MonoclonalService {

    @GET("/")
    suspend fun getMonoclonalList() : Monoclonal

}