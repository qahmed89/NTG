package com.ahmed.ntg.domain.use_case

import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.domain.model.Symbols
import com.ahmed.ntg.domain.repository.FixerRepository
import javax.inject.Inject

class getSymbolsUseCase @Inject constructor(
    private val fixerRepository: FixerRepository
) {

    suspend operator fun invoke() :Resource<Symbols>{
        return fixerRepository.getSymbolsDto()
    }
}