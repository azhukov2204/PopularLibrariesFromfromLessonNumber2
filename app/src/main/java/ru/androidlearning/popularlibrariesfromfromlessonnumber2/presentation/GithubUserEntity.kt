package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser

data class GithubUserEntity(
    val userId: Long,
    val login: String,
    val avatarUrl: String?
    ) {
    object Mapper {
        fun map(user: GithubUser): GithubUserEntity =
            GithubUserEntity(
                user.userId,
                user.login.uppercase(),
                user.avatarUrl
            )
    }
}
