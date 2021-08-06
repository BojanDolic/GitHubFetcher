package com.electrocoder.githubfetcher.models

import com.google.gson.annotations.SerializedName

data class Repo(

    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val repoName: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("commits_url")
    val commitsUrl: String = "",

    @SerializedName("watchers")
    val watchers: Int = 0,

    @SerializedName("open_issues")
    val openIssues: Int = 0
)