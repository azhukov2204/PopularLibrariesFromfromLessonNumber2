package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cloud

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.api.GitHubApi
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.GitHubUserDataSource

class GitHubUserDataSourceCloud(private val gitHubApi: GitHubApi) : GitHubUserDataSource {
    override fun getUsers(): Single<List<GithubUser>> =
        gitHubApi.getUsers()

    override fun getUserByLogin(login: String): Maybe<GithubUser> =
        gitHubApi.getUserByLogin(login)
}
