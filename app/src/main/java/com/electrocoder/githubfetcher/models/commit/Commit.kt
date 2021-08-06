package com.electrocoder.githubfetcher.models.commit

import com.electrocoder.githubfetcher.models.User
import com.google.gson.annotations.SerializedName

data class Commit(
    @SerializedName("node_id") val id: String = "",
    @SerializedName("commit") val commit: InnerCommit = InnerCommit(),
    @SerializedName("committer") val userCommited: User = User(),
)
