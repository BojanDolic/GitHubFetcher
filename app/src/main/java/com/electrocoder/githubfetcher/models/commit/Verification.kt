package com.electrocoder.githubfetcher.models.commit

import com.google.gson.annotations.SerializedName

data class Verification(
    @SerializedName("verified") val verified: Boolean = false,
    @SerializedName("reason") val reason: String = ""
)
