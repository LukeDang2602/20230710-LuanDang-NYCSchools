package com.example.a20230710_luandang_nycschools.di

import com.example.a20230710_luandang_nycschools.data.repository.SchoolRepositoryImpl
import com.example.a20230710_luandang_nycschools.domain.repository.SchoolRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSchoolRepository(schoolRepositoryImpl: SchoolRepositoryImpl): SchoolRepository
}