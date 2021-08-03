package com.electrocoder.githubfetcher.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.models.ApiResponse
import com.electrocoder.githubfetcher.models.RemoteFetcher
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.models.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(
    val api: GitHubApi
) {


    fun searchUsers(q: String): Flow<UsersResponse> = flow {

        val response = api.searchUsers(q)

        if(response.isSuccessful) {
            val body = response.body()

            if(body != null) {
                emit(body)
            }
            else emit(UsersResponse(poruka = "Body je null"))

        }

    }.flowOn(Dispatchers.IO)


    fun getUserByName(name: String) = flow {
        val response = api.getUserWithName(name)
        if(response.isSuccessful) {
            val body = response.body()

            if(body != null) {
                emit(body)
            } else emit(User(0, company = ""))

        } else emit(User(0, company = ""))
    }.flowOn(Dispatchers.IO)




//getRemoteDataLive {
        /*return if(call.isSuccessful) {
            Log.d("TAG", "searchUsers: PREUZIMANJE PODATAKA USPJEŠNO")

            flow<UsersResponse> {
                val body = call.body()
                if(body != null) {
                    Log.d(
                        "TAG",
                        "searchUsers: ${body.users.size}"
                    )
                    emit(body)
                } else Log.d("TAG", "searchUsers: BODY JE NULL")
            }
        } else flow { emit(UsersResponse()) }
    }*/

}