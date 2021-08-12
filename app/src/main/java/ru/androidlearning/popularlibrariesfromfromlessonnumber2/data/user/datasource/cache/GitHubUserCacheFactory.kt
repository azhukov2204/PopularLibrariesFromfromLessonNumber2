package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cache

object GitHubUserCacheFactory {
    fun create(): GitHubUserCache = GitHubUserCacheImpl()
}
