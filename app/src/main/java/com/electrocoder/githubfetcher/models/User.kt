package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("login") val name: String = "",
    @SerializedName("name") val fullName: String? = "",
    @SerializedName("company") val company: String? = "",
    @SerializedName("avatar_url") val avatarUrl: String? = "",
    @SerializedName("email") val email: String? = "",
    val location: String? = "",
    @SerializedName("public_repos") val publicRepos: Int = 0,
    @SerializedName("repos_url") val reposUrl: String = "",
    @SerializedName("followers") val followers: Int = 0,
    @SerializedName("following") val following: Int = 0
)

