package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.GitHubUserDataSource
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cache.GitHubUserCache

class GitHubUsersRepositoryImpl(
    private val gitHubUsersDataSource: GitHubUserDataSource,
    private val gitHubUserCache: GitHubUserCache
) : GitHubUsersRepository {

    override fun getUsers(): Observable<List<GitHubUser>> =
        Observable.merge(
            gitHubUserCache.getUsers().toObservable(),
            gitHubUsersDataSource.getUsers().flatMap {
                gitHubUserCache.retain(it)
            }.toObservable()
        )

    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        gitHubUserCache.getUserByLogin(login)
            .switchIfEmpty(gitHubUsersDataSource.getUserByLogin(login))

    override fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>> =
        gitHubUserCache.getUserRepositories(repositoriesUrl)
            .switchIfEmpty(gitHubUsersDataSource.getUserRepositories(repositoriesUrl)
                .flatMap { gitHibUserRepos -> gitHubUserCache.retain(repositoriesUrl, gitHibUserRepos).toMaybe() })

    override fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo> =
        gitHubUserCache.getUserRepositoryInfo(repositoryUrl)
            .switchIfEmpty(gitHubUsersDataSource.getUserRepositoryInfo(repositoryUrl)
                .flatMap { gitHubUserRepoInfo -> gitHubUserCache.retain(repositoryUrl, gitHubUserRepoInfo).toMaybe() })
}
