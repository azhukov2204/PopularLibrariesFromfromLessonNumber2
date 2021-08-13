package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.datasource.cache

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

class GitHubUserCacheImpl : GitHubUserCache {
    private val usersCache = mutableListOf<GitHubUser>()
    private val userRepositoriesCache = mutableMapOf<String, List<GitHubUserRepo>>()
    private val userRepoInfoCache = mutableMapOf<String, GitHubUserRepoInfo>()

    override fun retain(users: List<GitHubUser>): Single<List<GitHubUser>> =
        Single.fromCallable {
            usersCache.clear()
            usersCache.addAll(users)
            usersCache
        }

    override fun retain(repositoriesUrl: String, userRepositories: List<GitHubUserRepo>): Single<List<GitHubUserRepo>> =
        Single.fromCallable {
            userRepositoriesCache.remove(repositoriesUrl)
            userRepositoriesCache[repositoriesUrl] = userRepositories
            userRepositoriesCache[repositoriesUrl]
        }


    override fun retain(repositoryUrl: String, gitHubUserRepoInfo: GitHubUserRepoInfo): Single<GitHubUserRepoInfo> =
        Single.fromCallable {
            userRepoInfoCache.remove(repositoryUrl)
            userRepoInfoCache[repositoryUrl] = gitHubUserRepoInfo
            userRepoInfoCache[repositoryUrl]
        }

    override fun getUsers(): Single<List<GitHubUser>> =
        Single.just(usersCache)


    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        usersCache.firstOrNull { githubUser -> githubUser.login.contentEquals(login, ignoreCase = true) }
            ?.let { githubUser -> Maybe.just(githubUser) }
            ?: Maybe.empty()

    override fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>> =
        userRepositoriesCache[repositoriesUrl]
            ?.let { userRepos -> Maybe.just(userRepos) }
            ?: Maybe.empty()

    override fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo> =
        userRepoInfoCache[repositoryUrl]
            ?.let { userRepoInfo -> Maybe.just(userRepoInfo) }
            ?: Maybe.empty()
}
