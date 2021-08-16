package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository

import android.content.Context
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.GitHubUserCacheFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.GitHubUserCloudFactory

object GitHubUsersRepositoryFactory {

    fun create(context: Context): GitHubUsersRepository =
        GitHubUsersRepositoryImpl(
            GitHubUserCloudFactory.create(),
            GitHubUserCacheFactory.create(context)
        )
}
