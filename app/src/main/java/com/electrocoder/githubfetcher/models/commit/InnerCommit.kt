package com.electrocoder.githubfetcher.models.commit

import com.electrocoder.githubfetcher.models.User
import com.google.gson.annotations.SerializedName

data class InnerCommit(
    @SerializedName("author") val author : Author = Author(),
    @SerializedName("message") val message : String = "",
    @SerializedName("comment_count") val comment_count : Int = 0,
    @SerializedName("verification") val verification: Verification = Verification()
)