package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource

import io.reactivex.rxjava3.core.Maybe
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

interface GitHubUserDataSource {
    fun getUsers(): Maybe<List<GitHubUser>>
    fun getUserByLogin(login: String): Maybe<GitHubUser>
    fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>>
    fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo>
}
