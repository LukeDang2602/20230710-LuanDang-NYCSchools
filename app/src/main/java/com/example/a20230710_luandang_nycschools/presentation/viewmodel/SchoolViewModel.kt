package com.example.a20230710_luandang_nycschools.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a20230710_luandang_nycschools.data.api.NetworkResult
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.model.SATScoreResponse
import com.example.a20230710_luandang_nycschools.domain.usecase.SchoolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val schoolUseCase: SchoolUseCase): ViewModel(){
    private val _schoolResult = MutableLiveData<NetworkResult<HighSchoolResponse>>(NetworkResult.Loading())
    val schoolResult: LiveData<NetworkResult<HighSchoolResponse>>
        get() = _schoolResult

    private val _SATResult =  MutableLiveData<NetworkResult<SATScoreResponse>>(NetworkResult.Loading())
    val SATResult: LiveData<NetworkResult<SATScoreResponse>>
        get() = _SATResult

    fun getSchools(){
        viewModelScope.launch(Dispatchers.IO) {
            schoolUseCase.getSchools()
                .collect{
                    _schoolResult.postValue(it)
                }
        }
    }

    fun getSATScores(dbn: String){
        viewModelScope.launch(Dispatchers.IO) {
            schoolUseCase.getSATScores(dbn)
                .collect{
                    _SATResult.postValue(it)
                }
        }
    }
}