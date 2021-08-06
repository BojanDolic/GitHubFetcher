package com.electrocoder.githubfetcher.models.commit

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("author") val name: String = "",
    @SerializedName("date") val date: String = ""
)
