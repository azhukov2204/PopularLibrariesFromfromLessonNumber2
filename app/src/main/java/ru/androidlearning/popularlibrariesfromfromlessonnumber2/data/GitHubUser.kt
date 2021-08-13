package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data

import com.google.gson.annotations.SerializedName

data class GitHubUser(
    @SerializedName("id") val userId: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("repos_url") val reposUrl: String?
)
