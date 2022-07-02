package com.ahmed.ntg.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmed.ntg.common.Resource
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
        _state.value = _state.value.copy(isLoading = true, showNetworkScreen = false)
        viewModelScope.launch {
            val symbols = fixerUserCase.getSymbolsUseCase()
            when (symbols) {
                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            fromList = symbols.data?.symbols,
                            toList = symbols.data?.symbols,
                            showNetworkScreen = false
                        )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        showNetworkScreen = true,
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )

                }
            }
        }
    }

    fun getLatestForFromValue(base: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val latest = fixerUserCase.getLatestUseCase(base)
            when (latest) {
                is Resource.Success -> {
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            latest = latest.data,
                            showNetworkScreen = false
                        )

                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        showNetworkScreen = true,
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


    fun onFromValueChanged(value: Float, rates: Float) {
        viewModelScope.launch {
            _state.value = _state.value.copy(fromValue = value, toValue = rates * value)
        }
    }

    fun onToValueChanged(value: Float) {
        viewModelScope.launch {
            _state.value = _state.value.copy(toValue = value)
        }


    }


    fun updateSelectionData(fromValue: String, toValue: String) {

        viewModelScope.launch {
            val getLatest = fixerUserCase.getLatestUseCase(toValue)
            _state.value =
                _state.value.copy(
                    fromSelectedValue = toValue,
                    ToSelectedValue = fromValue,
                    latest = getLatest.data,
                    toValue = _state.value.fromValue * getLatest.data?.rates?.get(fromValue)!!,
                    fromValue = _state.value.fromValue
                )
        }
    }
}
