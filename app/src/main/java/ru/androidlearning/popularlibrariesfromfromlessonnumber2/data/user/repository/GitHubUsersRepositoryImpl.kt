package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.GitHubUserDataSource
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cache.GitHubUserCache

class GitHubUsersRepositoryImpl(
    private val gitHubUsersDataSource: GitHubUserDataSource,
    private val gitHubUserCache: GitHubUserCache
) : GitHubUsersRepository {

    override fun getUsers(): Observable<List<GithubUser>> =
        Observable.merge(
            gitHubUserCache.getUsers().toObservable(),
            gitHubUsersDataSource.getUsers().flatMap {
                gitHubUserCache.retain(it)
            }.toObservable()
        )

    override fun getUserByLogin(login: String): Maybe<GithubUser> =
        gitHubUserCache.getUserByLogin(login)
            .switchIfEmpty(gitHubUsersDataSource.getUserByLogin(login))
}
