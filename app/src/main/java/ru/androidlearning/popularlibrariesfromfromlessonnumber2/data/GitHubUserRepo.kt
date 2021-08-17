package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data

import com.google.gson.annotations.SerializedName

data class GitHubUserRepo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("url") val url: String,
)
