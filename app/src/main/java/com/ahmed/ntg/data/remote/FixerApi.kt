package com.ahmed.ntg.data.remote

import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.data.remote.dto.SymbolsDto
import com.ahmed.ntg.data.remote.dto.TimeSeriesDto
import retrofit2.http.GET
import retrofit2.http.Query


interface FixerApi {

    @GET("symbols")
    suspend fun getSymbols(): SymbolsDto

    @GET("latest")
    suspend fun getLatest(
        @Query("base") base :String
    ): LatestDto

    @GET("timeseries")
    suspend fun getTimeSeries(
        @Query("start_date") startDate: String, @Query("end_date") endDate: String,
        @Query("base") base :String,
    ): TimeSeriesDto
}

