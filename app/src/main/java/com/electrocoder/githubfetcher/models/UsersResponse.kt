package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName


data class UsersResponse(
    @SerializedName("items") val users: List<User> = listOf()
)