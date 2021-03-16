package com.example.freetogame.base

sealed class ApiResult<out Success, out Failure> {
    data class Success<Success>(val value: Success) : ApiResult<Success, Nothing>()
    data class Failure<Failure>(val failureReason: String) : ApiResult<Nothing, Failure>()
}