package com.ahmed.ntg.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TimeSeriesDto(
    val base: String,
    @SerializedName("end_date")
    val endDate: String,
    val rates: Map<String, Map<String, Double>>
)
