package com.electrocoder.githubfetcher.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.data.RepositoriesPagingSource
import com.electrocoder.githubfetcher.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class Repository @Inject constructor(
    val api: GitHubApi
) {


    fun searchUsers(q: String): Flow<UsersResponse> = flow {
        try {
            val response = api.searchUsers(q)

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    emit(body)
                } else emit(UsersResponse(poruka = "Body je null"))

            }
        } catch (e: Exception) {
            Log.d("TAG", "getUserByName: ${e.message}")
        }

    }.flowOn(Dispatchers.IO)


    fun getUserByName(name: String) = flow {
        try {
            val response = api.getUserWithName(name)
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    emit(body)
                } else emit(User(0))

            } else emit(User(0))
        } catch (e: Exception) {
            Log.d("TAG", "getUserByName: ${e.message}")
        }

    }.flowOn(Dispatchers.IO)


    fun getUserRepositories(url: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = { RepositoriesPagingSource(api, url) }
        ).flow.flowOn(Dispatchers.IO)
    }

    //fun getRepositories(url: String, page: Int):
}