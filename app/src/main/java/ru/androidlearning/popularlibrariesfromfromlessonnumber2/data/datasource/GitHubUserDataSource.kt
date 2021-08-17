package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

interface GitHubUserDataSource {
    fun getUsers(): Single<List<GitHubUser>>
    fun getUserByLogin(login: String): Maybe<GitHubUser>
    fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>>
    fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo>
}
