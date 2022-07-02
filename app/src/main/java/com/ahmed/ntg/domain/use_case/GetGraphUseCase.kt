package com.ahmed.ntg.domain.use_case

import com.ahmed.ntg.presentation.screen.details.compose.LineChartData

class GetGraphUseCase {
    operator fun invoke(
        rates: Map<String, Map<String, Float>>,
        toSelection: String
    ): LineChartData {
        var lineChartData = ArrayList<LineChartData.Point>()
        rates.keys.toList()
            .zip(rates.values.map {
                it.filterKeys { key ->
                    key.contains(
                        toSelection
                    )
                }
            }.flatMap { it.values }) { data, value ->
                lineChartData.add(
                    LineChartData.Point(
                        label = data,
                        value = value
                    )
                )
            }
        return LineChartData(points = lineChartData)
    }
}