package com.ahmed.ntg.presentation.screen.details

import com.ahmed.ntg.domain.model.TimeSeries
import com.ahmed.ntg.presentation.screen.details.compose.LineChartData

data class DetailsState(
    val isLoading: Boolean = true,
    val timeSeries: TimeSeries? = null,
    val isNetworkError: Boolean = false,
    val graphData: LineChartData = LineChartData(points = emptyList()),
    val otherCountery: Map<String, Float> = emptyMap()

)
