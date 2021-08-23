package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud

import io.reactivex.rxjava3.core.Maybe
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.api.GitHubApi

class GitHubUserCloudImpl(private val gitHubApi: GitHubApi) :
    GitHubUserCloud {
    override fun getUsers(): Maybe<List<GitHubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        gitHubApi.getUserByLogin(login)

    override fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>> =
        gitHubApi.getUserRepositories(repositoriesUrl)

    override fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo> =
        gitHubApi.getUserRepositoryInfo(repositoryUrl)
}
