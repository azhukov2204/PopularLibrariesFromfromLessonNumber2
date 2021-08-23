package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.api


import io.reactivex.rxjava3.core.Maybe
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

interface GitHubApi {
    @GET("/users")
    fun getUsers(
        @Query("since") since: Int? = null
    ): Maybe<List<GitHubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(
        @Path("username") login: String
    ): Maybe<GitHubUser>

    @GET
    fun getUserRepositories(
        @Url repositoriesUrl: String
    ): Maybe<List<GitHubUserRepo>>

    @GET
    fun getUserRepositoryInfo(
        @Url repositoryUrl: String
    ): Maybe<GitHubUserRepoInfo>
}
