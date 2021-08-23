package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.api.GitHubApiFactory

object GitHubUserCloudFactory {
    fun create(): GitHubUserCloud = GitHubUserCloudImpl(GitHubApiFactory.create())
}
