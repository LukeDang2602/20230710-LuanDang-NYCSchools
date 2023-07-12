package com.example.a20230710_luandang_nycschools.di

import com.example.a20230710_luandang_nycschools.data.api.Constants.BASE_URL
import com.example.a20230710_luandang_nycschools.data.api.SchoolApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//This module provides injections
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverter(): Converter.Factory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        baseUrl: String,
        converter: Converter.Factory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideSchoolApi(
        retrofit: Retrofit
    ): SchoolApi{
        return  retrofit.create(SchoolApi::class.java)
    }
}