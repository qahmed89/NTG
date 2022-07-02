package com.ahmed.ntg.domain.repository

import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.domain.model.Symbols
import com.ahmed.ntg.domain.model.TimeSeries

interface FixerRepository {

    suspend fun getSymbolsDto(): Resource<Symbols>
    suspend fun getLatest(base: String): Resource<LatestDto>
    suspend fun getTimeSeries(
        startDate: String,
        endData: String,
        base: String
    ): Resource<TimeSeries>

}