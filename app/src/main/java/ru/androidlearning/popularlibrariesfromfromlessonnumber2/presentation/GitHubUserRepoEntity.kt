package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo

data class GitHubUserRepoEntity(
    val id: Int,
    val name: String,
    val repoUrl: String
) {
    object Mapper {
        fun map(repo: GitHubUserRepo): GitHubUserRepoEntity =
            GitHubUserRepoEntity(
                repo.id,
                repo.name.uppercase(),
                repo.url
            )
    }
}
