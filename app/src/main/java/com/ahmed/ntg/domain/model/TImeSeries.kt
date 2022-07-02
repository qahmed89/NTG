package com.ahmed.ntg.domain.model

import com.ahmed.ntg.data.remote.dto.TimeSeriesDto
import com.google.gson.annotations.SerializedName
import java.util.*

data class TimeSeries(
    val base: String,
    val endDate: String,
    val rates: Map<String, Map<String, Float>>,
    val otherCurrency: Map<String,Float>
)
