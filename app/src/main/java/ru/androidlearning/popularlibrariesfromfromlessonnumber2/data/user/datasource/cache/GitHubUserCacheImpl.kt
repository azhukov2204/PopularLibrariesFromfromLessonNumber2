package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cache

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser

class GitHubUserCacheImpl : GitHubUserCache {
    private val cache = mutableListOf<GithubUser>()

    override fun retain(users: List<GithubUser>): Single<List<GithubUser>> =
        Single.fromCallable {
            cache.clear()
            cache.addAll(users)
            cache
        }

    override fun getUsers(): Single<List<GithubUser>> =
        Single.just(cache)


    override fun getUserByLogin(login: String): Maybe<GithubUser> =
        cache.firstOrNull { githubUser -> githubUser.login.contentEquals(login, ignoreCase = true) }
            ?.let { githubUser -> Maybe.just(githubUser) }
            ?: Maybe.empty()
}
