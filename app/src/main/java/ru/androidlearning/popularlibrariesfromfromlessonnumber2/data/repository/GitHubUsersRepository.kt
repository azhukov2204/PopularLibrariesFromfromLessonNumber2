package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

interface GitHubUsersRepository {
    fun getUsers(): Observable<List<GitHubUser>>
    fun getUserByLogin(login: String): Maybe<GitHubUser>
    fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>>
    fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo>
}
