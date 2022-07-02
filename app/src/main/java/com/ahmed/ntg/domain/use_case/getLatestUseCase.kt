package com.ahmed.ntg.domain.use_case

import com.ahmed.ntg.common.Resource
import com.ahmed.ntg.data.remote.dto.LatestDto
import com.ahmed.ntg.domain.repository.FixerRepository
import javax.inject.Inject

class getLatestUseCase @Inject constructor(
    private val fixerRepository: FixerRepository
) {

    suspend operator fun invoke(base:String): Resource<LatestDto> {
        return fixerRepository.getLatest(base)
    }
}