package com.electrocoder.githubfetcher.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.data.CommitsPagingSource
import com.electrocoder.githubfetcher.data.RepositoriesPagingSource
import com.electrocoder.githubfetcher.data.UsersSearchPagingSource
import com.electrocoder.githubfetcher.models.*
import com.electrocoder.githubfetcher.models.commit.Commit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject


class Repository @Inject constructor(
    val api: GitHubApi
) {

    fun searchUsers(search: String): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UsersSearchPagingSource(api, search) }
        ).flow.flowOn(Dispatchers.IO)
    }


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
            emit(null)
        }

    }.flowOn(Dispatchers.IO)


    /**
     * Function which requests all public user repositories
     * @param url full url to fetch repos from
     *
     * @return flow of paginated data
     */
    fun getUserRepositories(url: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RepositoriesPagingSource(api, url) }
        ).flow.flowOn(Dispatchers.IO)
    }

    /**
     * Function which requests repository commits using url
     * and returns paginated data
     * @param url url to fetch commits from
     *
     * @return flow of paginated data
     */
    fun getRepoCommits(url: String): Flow<PagingData<Commit>> {
        val formattedUrl = url.replace("{/sha}", "")
        return Pager(
            config = PagingConfig(
                pageSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CommitsPagingSource(api, formattedUrl) }
        ).flow.flowOn(Dispatchers.IO)
    }

}