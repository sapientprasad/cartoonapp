package com.prasad.domain.common

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val errorCode: Int, val errorMessage: String) : Result<Nothing>()
}