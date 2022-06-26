package com.ahmed.ntg.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class TimeSeries(
    val base: String,
    val endDate: String,
    val rates: Map<String, Map<String, Double>>
)

