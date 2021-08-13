package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cloud

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.api.GitHubApi
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.GitHubUserDataSource

class GitHubUserDataSourceCloud(private val gitHubApi: GitHubApi) : GitHubUserDataSource {
    override fun getUsers(): Single<List<GitHubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        gitHubApi.getUserByLogin(login)

    override fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>> =
        gitHubApi.getUserRepositories(repositoriesUrl)

    override fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo> =
        gitHubApi.getUserRepositoryInfo(repositoryUrl)
}
