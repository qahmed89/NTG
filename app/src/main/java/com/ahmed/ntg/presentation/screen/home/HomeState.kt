package com.ahmed.ntg.presentation.screen.home

import com.ahmed.ntg.data.remote.dto.LatestDto

data class HomeState(
    val isLoading: Boolean = false,
    val fromList: List<String>? = emptyList(),
    val toList: List<String>? = emptyList(),
    val latest: LatestDto? = null,
    val fromValue: Double = 1.0,
    val toValue: Double = 1.0,
    val fromSelectedValue: String = "",
    val ToSelectedValue: String = "",
    val showNetworkScreen: Boolean = false

)