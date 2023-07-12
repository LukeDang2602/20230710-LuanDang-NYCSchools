package com.example.a20230710_luandang_nycschools.data.repository

import com.example.a20230710_luandang_nycschools.data.api.NetworkResult
import com.example.a20230710_luandang_nycschools.data.api.SchoolApi
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.model.SATScoreResponse
import com.example.a20230710_luandang_nycschools.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//Implementation of school repository interface. Here will will implement 2 methods to get data from apis
class SchoolRepositoryImpl @Inject constructor(private val schoolApi: SchoolApi): SchoolRepository {

    //method to get schools
    override suspend fun getSchools(): Flow<NetworkResult<HighSchoolResponse>> = flow{
        try {
            val response = schoolApi.getSchools()
            with(response){
                if(this.isSuccessful){
                    emit(NetworkResult.Success(this.body()))
                }else{
                    emit(NetworkResult.Error(this.code(),this.message()))
                }
            }
        }catch (e: Exception){
            emit(NetworkResult.Exception(e))
        }
    }

    //method to get SAT scores
    override suspend fun getSATScores(dbn: String): Flow<NetworkResult<SATScoreResponse>> = flow{
        try {
            val response = schoolApi.getSATScores(dbn)
            with(response){
                if(this.isSuccessful){
                    emit(NetworkResult.Success(this.body()))
                }else{
                    emit(NetworkResult.Error(this.code(),this.message()))
                }
            }
        }catch (e: Exception){
            emit(NetworkResult.Exception(e))
        }
    }
}