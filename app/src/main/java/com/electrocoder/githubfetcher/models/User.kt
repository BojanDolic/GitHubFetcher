package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    val id: Long,

    @SerializedName("login")
    val name: String = "",

    @SerializedName("company")
    val company: String? = "",

    @SerializedName("avatar_url")
    val avatarUrl: String? = "",

    val email: String? = "",
    val location: String? = "",

    @SerializedName("public_repos")
    val publicRepos: Int = 0,

    @SerializedName("followers")
    val followers: Int = 0,

    @SerializedName("following")
    val following: Int = 0
)

