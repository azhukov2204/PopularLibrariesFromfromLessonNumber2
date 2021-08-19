package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.GitHubUserCache
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.GitHubUserCloud
import javax.inject.Inject

class GitHubUsersRepositoryImpl @Inject constructor(
    private val gitHubUserCloud: GitHubUserCloud,
    private val gitHubUserCache: GitHubUserCache
) : GitHubUsersRepository {

    override fun getUsers(): Observable<List<GitHubUser>> =
        Observable.merge(
            gitHubUserCache.getUsers().toObservable(),
            gitHubUserCloud.getUsers().toObservable()
                .flatMap { gitHubUserCache.retain(it).toObservable() }
        )

    override fun getUserByLogin(login: String): Observable<GitHubUser> =
        Observable.merge(
            gitHubUserCache.getUserByLogin(login).toObservable(),
            gitHubUserCloud.getUserByLogin(login).toObservable()
                .flatMap { gitHubUserCache.retain(it).toObservable() }
        )

    override fun getUserRepositories(repositoriesUrl: String): Observable<List<GitHubUserRepo>> =
        Observable.merge(
            gitHubUserCache.getUserRepositories(repositoriesUrl).toObservable(),
            gitHubUserCloud.getUserRepositories(repositoriesUrl).toObservable()
                .flatMap { gitHubUserRepos -> gitHubUserCache.retain(repositoriesUrl, gitHubUserRepos).toObservable() }
        )

    override fun getUserRepositoryInfo(repositoryUrl: String): Observable<GitHubUserRepoInfo> =
        Observable.merge(
            gitHubUserCache.getUserRepositoryInfo(repositoryUrl).toObservable(),
            gitHubUserCloud.getUserRepositoryInfo(repositoryUrl).toObservable()
                .flatMap { gitHubUserRepoInfo -> gitHubUserCache.retain(repositoryUrl, gitHubUserRepoInfo).toObservable() }
        )

    override fun saveAvatarCachedPath(userId: Long, outputFileName: String): Single<String> =
        gitHubUserCache.saveAvatarCachedPath(userId, outputFileName)
}
