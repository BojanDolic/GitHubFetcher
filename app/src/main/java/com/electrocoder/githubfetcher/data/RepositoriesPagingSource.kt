package com.electrocoder.githubfetcher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.models.Repo
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoriesPagingSource(
    val api: GitHubApi,
    val url: String
) : PagingSource<Int, Repo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {

        val position = params.key ?: 1 // Starting page is 1

        return try {
            val response = api.getUserRepositories(url, position)
            val repositories = response.body()

            if(repositories != null) {
                val nextPage = if (repositories.isEmpty()) {
                    null
                } else {
                    position + 1
                }

                LoadResult.Page(
                    data = repositories,
                    prevKey = if (position == 1) null else position + 1,
                    nextKey = nextPage
                )
            }
            else {
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

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)

        }
    }

}