package com.electrocoder.githubfetcher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.electrocoder.githubfetcher.api.GitHubApi
import com.electrocoder.githubfetcher.models.commit.Commit
import retrofit2.HttpException
import java.io.IOException

class CommitsPagingSource(
    val api: GitHubApi,
    val commitUrl: String
) : PagingSource<Int, Commit>() {
    override fun getRefreshKey(state: PagingState<Int, Commit>): Int? {
        return state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Commit> {

        val position = params.key ?: 1

        return try {
            val response = api.getRepoCommits(commitUrl, position)
            val commits = response.body()

            val nextPage = if (commits?.isEmpty() == true) {
                null
            } else {
                position + 1 // 30 elements by default
            }

            LoadResult.Page(
                data = commits ?: listOf(),
                prevKey = if (position == 1) null else position + 1,
                nextKey = nextPage
            )

        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

}