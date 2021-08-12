package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cache.GitHubUserCacheFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cloud.GitHubUserDataSourceFactory

object GitHubUsersRepositoryFactory {
    private val gitHubUsersRepository: GitHubUsersRepository = GitHubUsersRepositoryImpl(
        GitHubUserDataSourceFactory.create(),
        GitHubUserCacheFactory.create()
    )

    fun getRepository(): GitHubUsersRepository = gitHubUsersRepository
}
