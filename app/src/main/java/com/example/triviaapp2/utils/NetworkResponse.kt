package com.example.triviaapp2.utils

sealed class NetworkResponse<T>(var data: T? = null, val message: String? = null) {
    data class Loading<T>(val newData: T? = null, val newMessage: String? = null) : NetworkResponse<T>()
    data class Success<T>(val newData: T?) : NetworkResponse<T>(data = newData)
    data class Error<T>(val errorMessage: String?) : NetworkResponse<T>(message = errorMessage)
}