package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser

interface GitHubUserDataSource {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String): Maybe<GithubUser>
}
