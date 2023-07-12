package com.example.a20230710_luandang_nycschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.a20230710_luandang_nycschools.data.api.NetworkResult
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponse
import com.example.a20230710_luandang_nycschools.data.model.HighSchoolResponseEntity
import com.example.a20230710_luandang_nycschools.domain.repository.SchoolRepository
import com.example.a20230710_luandang_nycschools.domain.usecase.SchoolUseCase
import com.example.a20230710_luandang_nycschools.presentation.viewmodel.SchoolViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//Unit Testing for school viewmodel
class SchoolViewModelTest {
    @get:Rule
    val instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = MainDispatcherRule()

    private lateinit var schoolViewModel: SchoolViewModel
    private lateinit var repository: SchoolRepository

    @Before
    fun setup(){
        repository = mockk()
        schoolViewModel = SchoolViewModel(SchoolUseCase(repository))
    }

    @Test
    fun `getSchools should return Success state when API cal is successful`() = runTest {
        //Mock the response from the repository
        val response = HighSchoolResponse()
        coEvery { repository.getSchools() } returns flow{ emit(NetworkResult.Success(response))}

        //Create a mock observer
        val schoolResultObserver = mockk<Observer<NetworkResult<HighSchoolResponse>>>(relaxed = true)
        schoolViewModel.schoolResult.observeForever(schoolResultObserver)

        //Call the method to be tested
        schoolViewModel.getSchools()

        //Verify that schoolResult get expected data
        verify { schoolResultObserver.onChanged(NetworkResult.Success(HighSchoolResponse())) }
    }

}