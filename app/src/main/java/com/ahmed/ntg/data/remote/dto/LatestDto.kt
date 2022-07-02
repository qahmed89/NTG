package com.ahmed.ntg.data.remote.dto

import java.util.*

data class LatestDto(
    val base : String,
    val date: String,
    val rates : Map<String, Float>
)
