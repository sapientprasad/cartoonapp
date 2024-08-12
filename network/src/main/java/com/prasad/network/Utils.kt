package com.prasad.network

import com.prasad.domain.common.Result
import retrofit2.Response
import javax.inject.Inject

class Utils @Inject constructor() {
    suspend fun <T, R> safeApiCall(
        call: suspend () -> Response<T>,
        transform: T.() -> R
    ): Result<R> =
        try {
            val response = call.invoke()
            with(response)
            {
                if (isSuccessful) {
                    body()?.let {
                        Result.Success(it.transform())
                    } ?: run {
                        Result.Error(ERROR_CODE, ERROR_MESSAGE)
                    }
                } else {
                    Result.Error(code(), message())
                }
            }
        } catch (exception: Exception) {
            Result.Error(ERROR_CODE, ERROR_MESSAGE)
        }

    private companion object {
        private const val ERROR_CODE = -1
        private const val ERROR_MESSAGE = "You shouldn't be here!!"
    }
}