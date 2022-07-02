package com.ahmed.ntg.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.FixerApi
import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.data.remote.dto.TimeSeriesDto
import com.ahmed.ntg.data.remote.dto.toSymbols
import com.ahmed.ntg.data.remote.dto.toTimeSeries
import com.ahmed.ntg.domain.model.Symbols
import com.ahmed.ntg.domain.model.TimeSeries
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

    override suspend fun getLatest(base:String): Resource<LatestDto> {
        val response = try {
            fixerApi.getLatest(base)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override suspend fun getTimeSeries(
        startDate: String,
        endData: String,
        base: String

    ): Resource<TimeSeries> {
        val response = try {
            fixerApi.getTimeSeries(startDate, endData, base).toTimeSeries()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

}