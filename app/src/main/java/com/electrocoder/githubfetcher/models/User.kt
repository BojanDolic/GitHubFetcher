package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName

data class User(
    val name: String = "",
    val company: String? = "",

    @SerializedName("avatar_url")
    val avatarUrl: String? = "",

    val email: String? = "",
    val location: String? = ""

) {
}