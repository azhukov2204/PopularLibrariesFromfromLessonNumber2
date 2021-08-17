package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cache

object GitHubUserCacheFactory {
    fun create(): GitHubUserCache = GitHubUserCacheImpl()
}
