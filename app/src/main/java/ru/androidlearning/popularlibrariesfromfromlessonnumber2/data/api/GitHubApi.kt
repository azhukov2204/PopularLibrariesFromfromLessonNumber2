package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.api


import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GithubUser

interface GitHubApi {
    @GET("/users")
    fun getUsers(
        @Query("since") since: Int? = null
    ): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Maybe<GithubUser>
}
