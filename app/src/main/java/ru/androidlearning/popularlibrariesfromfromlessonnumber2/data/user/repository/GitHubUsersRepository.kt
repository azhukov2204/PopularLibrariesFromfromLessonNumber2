package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser

interface GitHubUsersRepository {
    fun getUsers(): Observable<List<GithubUser>>
    fun getUserByLogin(login: String): Maybe<GithubUser>
}
