package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cache

import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.GitHubUserDataSource

interface GitHubUserCache : GitHubUserDataSource {
    fun retain(users: List<GitHubUser>): Single<List<GitHubUser>>
    fun retain(repositoriesUrl: String, userRepositories: List<GitHubUserRepo>): Single<List<GitHubUserRepo>>
    fun retain(repositoryUrl: String, gitHubUserRepoInfo: GitHubUserRepoInfo): Single<GitHubUserRepoInfo>
}
