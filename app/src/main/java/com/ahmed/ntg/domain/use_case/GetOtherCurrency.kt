package com.ahmed.ntg.domain.use_case

import com.ahmed.ntg.common.Constants.LIST_POPULAR_CURRENCY
import com.ahmed.ntg.domain.model.TimeSeries
import com.ahmed.ntg.presentation.screen.details.Details

class GetOtherCurrency {

    operator fun invoke(base: Details?, timeSeries: TimeSeries?): Map<String, Float>{
        return timeSeries?.otherCurrency?.filterKeys { keys ->
                !keys.contains(
                    base?.toBase ?: ""
                ) && keys in LIST_POPULAR_CURRENCY
            } ?: emptyMap()
        }
}