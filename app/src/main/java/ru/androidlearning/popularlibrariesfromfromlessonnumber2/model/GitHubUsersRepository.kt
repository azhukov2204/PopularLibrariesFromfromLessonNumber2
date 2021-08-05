package ru.androidlearning.popularlibrariesfromfromlessonnumber2.model

interface GitHubUsersRepository {
    fun getUsers(): List<GithubUser>
    fun getLoginByUserId(userId: Long): String?
}
