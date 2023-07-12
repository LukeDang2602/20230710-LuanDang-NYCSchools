package com.example.a20230710_luandang_nycschools.data.api

import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.api.Constants.SAT_ENDPOINT
import com.example.a20230710_luandang_nycschools.data.api.Constants.SCHOOL_ENDPOINT
import com.example.a20230710_luandang_nycschools.data.model.SATScoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit api interface that defines the GET methods to get list of schools and SAT scores
interface SchoolApi {
    @GET(SCHOOL_ENDPOINT)
    suspend fun getSchools(): Response<HighSchoolResponse>

    @GET(SAT_ENDPOINT)
    suspend fun getSATScores(@Query("dbn") dbn: String): Response<SATScoreResponse>
}