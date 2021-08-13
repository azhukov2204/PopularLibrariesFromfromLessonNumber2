package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser

data class GitHubUserEntity(
    val userId: Long,
    val login: String,
    val avatarUrl: String?,
    val userReposUrl: String?
) {
    object Mapper {
        fun map(user: GitHubUser): GitHubUserEntity =
            GitHubUserEntity(
                user.userId,
                user.login.uppercase(),
                user.avatarUrl,
                user.reposUrl
            )
    }
}
