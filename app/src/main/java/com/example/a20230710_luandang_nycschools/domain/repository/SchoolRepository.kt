package com.example.a20230710_luandang_nycschools.domain.repository

import com.example.a20230710_luandang_nycschools.data.api.NetworkResult
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.model.SATScoreResponse
import kotlinx.coroutines.flow.Flow

//Repository interface that holds the methods to retrieve schools and SAT scores from apis
interface SchoolRepository {
    //method to get schools
    suspend fun getSchools(): Flow<NetworkResult<HighSchoolResponse>>

    //method to get scores
    suspend fun getSATScores(dbn: String): Flow<NetworkResult<SATScoreResponse>>
}