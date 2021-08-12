package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.cloud

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.api.GitHubApiFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource.GitHubUserDataSource

object GitHubUserDataSourceFactory {
    fun create(): GitHubUserDataSource = GitHubUserDataSourceCloud(GitHubApiFactory.create())
}
