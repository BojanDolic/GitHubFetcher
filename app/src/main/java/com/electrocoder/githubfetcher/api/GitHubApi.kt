package com.electrocoder.githubfetcher.api

import com.electrocoder.githubfetcher.models.ApiResponse
import com.electrocoder.githubfetcher.models.User

public interface GitHubApi {


    fun searchUsers(): ApiResponse<List<User>>

}