package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cache

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.GitHubUserDataSource

interface GitHubUserCache : GitHubUserDataSource {
    fun retain(users: List<GithubUser>): Single<List<GithubUser>>
}
