package com.ahmed.ntg.data.remote.dto

import android.os.Build
import androidx.annotation.RequiresApi
import com.ahmed.ntg.domain.model.TimeSeries
import com.google.gson.annotations.SerializedName
import java.util.stream.Collectors

data class TimeSeriesDto(
    val base: String,
    @SerializedName("end_date")
    val endDate: String,
    val rates: Map<String, Map<String, Float>>
)


fun TimeSeriesDto.toTimeSeries(): TimeSeries {
    return TimeSeries(
        base = base,
        rates = rates,
        otherCurrency = rates.values.asSequence()
            .flatMap {
                it.asSequence()
            }.map { it.key to it.value }.toMap(),
        endDate = endDate

    )
}
