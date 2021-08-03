package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName


data class UsersResponse(

    var poruka: String = "",

    @SerializedName("items")
    val users: List<User> = listOf()
)