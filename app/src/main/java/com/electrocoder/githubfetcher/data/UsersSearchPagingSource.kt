package com.electrocoder.githubfetcher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.models.UsersResponse
import retrofit2.HttpException
import java.io.IOException

class UsersSearchPagingSource(
    private val api: GitHubApi,
    private val searchQuery: String
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val position = params.key ?: 1

        return try {
            val response = api.searchUsers(searchQuery, position)
            val users = response.body()
            if(users != null) {
                val nextPage = if (users.users.isEmpty()) {
                    null
                } else {
                    position + 1 // 30 elements by default
                }

                LoadResult.Page(
                    data = users.users,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(
                    HttpException(response)
                )
            }
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }

    }


}