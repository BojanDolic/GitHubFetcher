package com.electrocoder.githubfetcher.api

import androidx.lifecycle.LiveData
import com.electrocoder.githubfetcher.models.ApiResponse
import com.electrocoder.githubfetcher.models.Repo
import com.electrocoder.githubfetcher.models.User
import com.electrocoder.githubfetcher.models.UsersResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApi {

    @GET("search/users")
    suspend fun searchUsers(@Query("q") q: String): Response<UsersResponse>

    /**
     * Sends a GET request to server for user details
     * @param name name of the user for which we retrieve details
     */
    @GET("users/{username}")
    suspend fun getUserWithName(@Path("username") name: String): Response<User>

    @GET
    suspend fun getUserRepositories(
        @Url url: String,
        @Query("page") page: Int,
        @Query("sort") sort: String = "created"): Response<List<Repo>>

}