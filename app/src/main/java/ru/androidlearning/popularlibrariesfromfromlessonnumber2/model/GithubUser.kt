package ru.androidlearning.popularlibrariesfromfromlessonnumber2.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val userId: Long,
    val login: String
) : Parcelable
