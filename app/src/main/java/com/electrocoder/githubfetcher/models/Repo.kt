package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName

data class Repo(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val repoName: String = "",

    @SerializedName("owner")
    val owner: User?,


    @SerializedName("description")
    val description: String = "",

    @SerializedName("commits_url")
    val commitsUrl: String = "",

    @SerializedName("watchers")
    val watchers: Int,

    @SerializedName("open_issues")
    val openIssues: Int
)