package com.example.a20230710_luandang_nycschools.data.api

//sealed class represents the results from making network call
sealed class NetworkResult<T: Any>{
    data class Success<T: Any>(val data: T?): NetworkResult<T>()
    data class Error<T: Any>(val code: Int, val mess: String?): NetworkResult<T>()
    data class Exception<T: Any>(val e: Throwable): NetworkResult<T>()
    class Loading<T: Any>: NetworkResult<T>()
}
