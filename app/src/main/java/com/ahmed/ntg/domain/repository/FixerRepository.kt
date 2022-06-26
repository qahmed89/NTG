package com.ahmed.ntg.domain.repository

import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.data.remote.dto.TimeSeriesDto
import com.ahmed.ntg.domain.model.Symbols

interface FixerRepository {

    suspend fun getSymbolsDto(): Resource<Symbols>
    suspend fun getLatest(): Resource<LatestDto>
    suspend fun getTimeSeries(
        startDate: String,
        endData: String,
        base: String
    ): Resource<TimeSeriesDto>

}