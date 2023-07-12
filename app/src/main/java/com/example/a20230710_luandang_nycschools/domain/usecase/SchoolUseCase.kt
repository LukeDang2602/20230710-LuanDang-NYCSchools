package com.example.a20230710_luandang_nycschools.domain.usecase

import com.example.a20230710_luandang_nycschools.data.api.NetworkResult
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.model.SATScoreResponse
import com.example.a20230710_luandang_nycschools.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SchoolUseCase @Inject constructor(private val schoolRepository: SchoolRepository): SchoolRepository {
    override suspend fun getSchools(): Flow<NetworkResult<HighSchoolResponse>> = schoolRepository.getSchools()

    override suspend fun getSATScores(dbn: String): Flow<NetworkResult<SATScoreResponse>> = schoolRepository.getSATScores(dbn)
}