package com.electrocoder.githubfetcher.api

import com.electrocoder.githubfetcher.models.*
import com.electrocoder.githubfetcher.models.commit.Commit
import retrofit2.Response
import retrofit2.http.*

interface GitHubApi {

    /**
     * Request for searching users based on their GitHub username
     * @param q query
     */
    @GET("search/users")
    suspend fun searchUsers(@Query("q") q: String): Response<UsersResponse>

    /**
     * Sends a GET request to server for user details
     * @param name name of the user for which we retrieve details
     */
    @GET("users/{username}")
    suspend fun getUserWithName(@Path("username") name: String): Response<User>

    /**
     * Fetches user repositories via url
     * @param url url to fetch data from
     * @param sort sorting data based on date of creation
     */
    @GET
    suspend fun getUserRepositories(
        @Url url: String,
        @Query("page") page: Int,
        @Query("sort") sort: String = "created"): Response<List<Repo>>


    @GET
    suspend fun getRepoCommits(
        @Url url: String,
        @Query("page") page: Int): Response<List<Commit>>

}