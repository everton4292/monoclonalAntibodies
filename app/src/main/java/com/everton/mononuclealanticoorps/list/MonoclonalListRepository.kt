package com.everton.mononuclealanticoorps.list

import com.everton.mononuclealanticoorps.model.Monoclonal
import com.everton.mononuclealanticoorps.list.MonoclonalService

interface ListRepository {
    suspend fun getMonoclonalList(): Monoclonal
}

class ListRepositoryResponse (private val monoclonalService: MonoclonalService): ListRepository{

    override suspend fun getMonoclonalList(): Monoclonal = monoclonalService.getMonoclonalList()

}