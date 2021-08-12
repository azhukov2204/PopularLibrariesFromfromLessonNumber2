package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @SerializedName("id") val userId: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String? = null,
) : Parcelable
