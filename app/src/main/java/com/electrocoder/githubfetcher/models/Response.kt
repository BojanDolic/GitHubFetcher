package com.electrocoder.githubfetcher.models

data class ApiResponse<out T>(
    val status: Status,
    val data: T?,
    val message: String = ""
) {

    enum class Status {
        STATUS_ERROR,
        STATUS_LOADING,
        STATUS_SUCCESS
    }

    companion object {

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(Status.STATUS_SUCCESS, data)
        }

        fun <T> loading(data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.STATUS_LOADING, data)
        }

        fun <T> error(message: String, data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.STATUS_ERROR, data, message)
        }

    }


}