package ru.androidlearning.popularlibrariesfromfromlessonnumber2.model

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

interface GitHubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>
    fun getLoginByUserId(userId: Long): Maybe<String>
}
