package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cache.GitHubUserCacheFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cloud.GitHubUserDataSourceFactory

object GitHubUsersRepositoryFactory {
    private val gitHubUsersRepository: GitHubUsersRepository = GitHubUsersRepositoryImpl(
        GitHubUserDataSourceFactory.create(),
        GitHubUserCacheFactory.create()
    )

    fun getRepository(): GitHubUsersRepository = gitHubUsersRepository
}
