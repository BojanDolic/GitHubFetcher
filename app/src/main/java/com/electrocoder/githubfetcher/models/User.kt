package com.electrocoder.githubfetcher.models

import com.squareup.moshi.Json

data class User(
    val name: String = "",
    val company: String? = "",

    @Json(name = "avatar_url")
    val avatarUrl: String? = "",

    val email: String? = "",
    val location: String? = ""

) {
}