package com.ahmed.ntg.data.repository

import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.FixerApi
import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.data.remote.dto.TimeSeriesDto
import com.ahmed.ntg.data.remote.dto.toSymbols
import com.ahmed.ntg.domain.model.Symbols
import com.ahmed.ntg.domain.repository.FixerRepository
import javax.inject.Inject

class FixerRepositoryImpl @Inject constructor(
    private val fixerApi: FixerApi
) : FixerRepository {


    override suspend fun getSymbolsDto(): Resource<Symbols> {
        val response = try {
            fixerApi.getSymbols().toSymbols()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)

    }

    override suspend fun getLatest(): Resource<LatestDto> {
        val response = try {
            fixerApi.getLatest()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)

    }

    override suspend fun getTimeSeries(
        startDate: String,
        endData: String,
        base: String

    ): Resource<TimeSeriesDto> {
        val response = try {
            fixerApi.getTimeSeries(startDate, endData, base)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

}