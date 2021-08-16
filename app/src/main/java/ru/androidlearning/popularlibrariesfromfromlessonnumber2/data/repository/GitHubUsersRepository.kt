package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

interface GitHubUsersRepository {
    fun getUsers(): Observable<List<GitHubUser>>
    fun getUserByLogin(login: String): Observable<GitHubUser>
    fun getUserRepositories(repositoriesUrl: String): Observable<List<GitHubUserRepo>>
    fun getUserRepositoryInfo(repositoryUrl: String): Observable<GitHubUserRepoInfo>
    fun saveAvatarCachedPath(userId: Long, outputFileName: String): Single<String>
}
