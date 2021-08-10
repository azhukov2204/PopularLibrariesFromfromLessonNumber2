package ru.androidlearning.popularlibrariesfromfromlessonnumber2.model

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

class GitHubUsersRepositoryImpl : GitHubUsersRepository {
    private val repositories = listOf(
        GithubUser(1, "login1"),
        GithubUser(2, "login2"),
        GithubUser(3, "login3"),
        GithubUser(4, "login4"),
        GithubUser(5, "login5")
    )

    override fun getUsers(): Single<List<GithubUser>> = Single.just(repositories)

    override fun getLoginByUserId(userId: Long): Maybe<String> =
        repositories.firstOrNull { githubUser -> githubUser.userId == userId }
            ?.let { githubUser -> Maybe.just((githubUser.login)) }
            ?: Maybe.empty()
}
