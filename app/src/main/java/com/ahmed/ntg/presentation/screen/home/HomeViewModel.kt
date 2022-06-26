package com.ahmed.ntg.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.domain.use_case.FixerUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fixerUserCase: FixerUserCase
) : ViewModel() {
    private var _state = MutableStateFlow(HomeState(isLoading = true))
    val state: StateFlow<HomeState> = _state

    init {
        getSymbols()
    }

    fun getSymbols(

    ) {
        viewModelScope.launch {

            val symbols = fixerUserCase.getSymbolsUseCase()
            when (symbols) {
                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(isLoading = false, fromList = symbols.data?.symbols)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        showNetworkScreen = true,
                        fromList = listOf(
                            "ADD",
                            "ASS",
                            "GGZ",
                            "KLS",
                            "ADN"
                        ),
                        toList = listOf(
                            "ADD",
                            "ASS",
                            "GGZ",
                            "KLS",
                            "ADN"
                        ),
                        isLoading = false
                    )

                }


            }
        }
    }

    fun getLatestForFromValue(base: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val latest = fixerUserCase.getLatestUseCase()
            when (latest) {
                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(isLoading = false, latest = latest.data)
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        showNetworkScreen = true,
                        latest = LatestDto(
                            base = _state.value.fromSelectedValue,
                            date = "2018-10-02",
                            rates = mapOf<String, Double>(
                                "ADD" to 10.255,
                                "ASS" to 11.255,
                                "GGZ" to 12.255,
                                "KLS" to 13.255,
                                "ADN" to 14.255
                            )
                        ),
                        toList = _state.value.fromList?.filter { !it.contains(base) },
                        isLoading = false
                    )

                }
            }
        }
    }

    fun onFromSelectedChanged(value: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(fromSelectedValue = value)
        }

    }


    fun onToSelectedChanged(value: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(ToSelectedValue = value)
        }

    }


    fun onFromValueChanged(value: Double, rates: Double) {
        viewModelScope.launch {
            _state.value = _state.value.copy(fromValue = value, toValue = rates * value)
        }

    }


    fun updateBoth(fromValue: String, toValue: String) {

        viewModelScope.launch {
            _state.value =
                _state.value.copy(fromSelectedValue = toValue, ToSelectedValue = fromValue)
        }
    }
}
