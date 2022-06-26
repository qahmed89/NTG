package com.ahmed.ntg.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.dto.TimeSeriesDto
import com.ahmed.ntg.domain.repository.FixerRepository
import com.ahmed.ntg.utils.Utils
import javax.inject.Inject

class GetTimeSeries  @Inject constructor(
    val fixerRepository: FixerRepository
){
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke (base : String) : Resource<TimeSeriesDto>{
        val endDate = Utils.getEndData()
        val startDate = Utils.getStartData()
        return fixerRepository.getTimeSeries(startDate,endDate,base)
    }
}