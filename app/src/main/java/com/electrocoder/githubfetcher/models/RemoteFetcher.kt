package com.electrocoder.githubfetcher.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.lang.Exception

abstract class RemoteFetcher {

    suspend fun <T> getRemoteDataLive(call: suspend () -> Response<T>): LiveData<ApiResponse<T>> = liveData(Dispatchers.IO) {
        try {
            val resultCall = call()
            emit(ApiResponse.loading()) // Loading

            if(resultCall.isSuccessful) {
                val body = resultCall.body()

                if(body != null)
                    emit(ApiResponse.success(body))
                else emit(ApiResponse.error("Error while fetching data (body == null", body))

            }

        } catch (e: Exception) {
            emit(ApiResponse.error(e.message ?: e.toString(), null))
        }


    }


}