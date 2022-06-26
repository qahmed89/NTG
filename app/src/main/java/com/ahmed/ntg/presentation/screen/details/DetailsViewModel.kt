package com.ahmed.ntg.presentation.screen.details

import androidx.lifecycle.ViewModel
import com.ahmed.ntg.domain.use_case.FixerUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fixerUserCase: FixerUserCase
) : ViewModel() {


}