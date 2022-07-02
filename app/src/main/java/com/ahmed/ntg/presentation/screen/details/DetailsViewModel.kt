package com.ahmed.ntg.presentation.screen.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.domain.use_case.FixerUserCase
import com.ahmed.ntg.domain.use_case.GetGraphUseCase
import com.ahmed.ntg.domain.use_case.GetOtherCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fixerUserCase: FixerUserCase
) : ViewModel() {
    private var _state = MutableStateFlow(DetailsState(isLoading = true))
    val state: StateFlow<DetailsState> = _state


    @RequiresApi(Build.VERSION_CODES.O)
    fun getTimeSeries(
        baseDetails: Details?
    ) {
        _state.value = _state.value.copy(isLoading = true, isNetworkError = false)
        viewModelScope.launch {

            when (val timeSeries = fixerUserCase.getTimeSeries(baseDetails?.fromBase ?: "")) {
                is Resource.Success -> {
                    val getGraphUseCase = GetGraphUseCase().invoke(
                        timeSeries.data?.rates ?: emptyMap(),
                        baseDetails?.toBase ?: ""
                    )
                    val  getOtherCurrency = GetOtherCurrency().invoke(baseDetails , timeSeries.data)
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            timeSeries = timeSeries.data,
                            graphData = getGraphUseCase,
                            otherCountery = getOtherCurrency
                        )

                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        isNetworkError = true,
                        isLoading = false
                    )

                }
                is Resource.Loading -> _state.value = _state.value.copy(
                    isLoading = true,
                    isNetworkError = false
                )
            }
        }
    }

}