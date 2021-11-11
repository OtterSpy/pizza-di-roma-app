package com.example.pizzadiromaapp.common

sealed class Resource<ResultType>(data: ResultType? = null, error: String? = null) {

    class Loading<ResultType> : Resource<ResultType>()

    data class Success<ResultType>(val data: ResultType) : Resource<ResultType>(data = data)

    data class Failure<ResultType>(val message: String) : Resource<ResultType>(error = message)

    companion object {
        fun <ResultType> loading() = Loading<ResultType>()

        fun <ResultType> success(data: ResultType) = Success(data)

        fun <ResultType> error(error: String) = Failure<ResultType>(error)
    }
}