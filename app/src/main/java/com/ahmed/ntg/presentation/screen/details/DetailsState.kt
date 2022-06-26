package com.ahmed.ntg.presentation.screen.details

import com.ahmed.ntg.domain.model.TimeSeries

data class DetailsState(
    val isLoading : Boolean = true,
    val timeSeries: TimeSeries ?= null,
    val isNetworkError : Boolean = false,

)
