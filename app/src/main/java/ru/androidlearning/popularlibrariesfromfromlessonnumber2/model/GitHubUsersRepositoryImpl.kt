package ru.androidlearning.popularlibrariesfromfromlessonnumber2.model

class GitHubUsersRepositoryImpl: GitHubUsersRepository {
    private val repositories = listOf(
        GithubUser(1, "login1"),
        GithubUser(2, "login2"),
        GithubUser(3, "login3"),
        GithubUser(4, "login4"),
        GithubUser(5, "login5")
    )

    override fun getUsers(): List<GithubUser> {
        return repositories
    }

    override fun getLoginByUserId(userId: Long) = repositories.firstOrNull { githubUser -> githubUser.userId == userId }?.login
}