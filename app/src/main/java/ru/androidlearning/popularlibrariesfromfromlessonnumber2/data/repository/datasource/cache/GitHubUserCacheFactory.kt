package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache

import android.content.Context
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.GitHubStorageFactory

object GitHubUserCacheFactory {
    fun create(context: Context): GitHubUserCache = GitHubUserCacheImpl(GitHubStorageFactory.create(context))
}
