package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.GitHubStorage
import javax.inject.Inject

class GitHubUserCacheImpl @Inject constructor(private val gitHubStorage: GitHubStorage) : GitHubUserCache {

    override fun retain(users: List<GitHubUser>): Single<List<GitHubUser>> =
        gitHubStorage
            .gitHubUserDao()
            .retain(users)
            .andThen(getUsers())
            .toSingle()

    override fun retain(user: GitHubUser): Single<GitHubUser> =
        gitHubStorage
            .gitHubUserDao()
            .retain(user)
            .andThen(getUserByLogin(user.login))
            .toSingle()

    override fun retain(repositoriesUrl: String, userRepositories: List<GitHubUserRepo>): Single<List<GitHubUserRepo>> =
        userRepositories
            .map { gitHubUseRepo -> gitHubUseRepo.apply { reposUrl = repositoriesUrl } }
            .let { gitHubUsers ->
                gitHubStorage
                    .gitHubUserRepoDao()
                    .retain(gitHubUsers)
            }
            .andThen(getUserRepositories(repositoriesUrl))
            .toSingle()

    override fun retain(repositoryUrl: String, gitHubUserRepoInfo: GitHubUserRepoInfo): Single<GitHubUserRepoInfo> {
        gitHubUserRepoInfo.repoUrl = repositoryUrl
        return gitHubStorage
            .gitHubUserRepoInfoDao()
            .retain(gitHubUserRepoInfo)
            .andThen(getUserRepositoryInfo(repositoryUrl))
            .toSingle()
    }

    override fun getUsers(): Maybe<List<GitHubUser>> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUsers()

    override fun getUserByLogin(login: String): Maybe<GitHubUser> =
        gitHubStorage
            .gitHubUserDao()
            .fetchUserByLogin(login)

    override fun getUserRepositories(repositoriesUrl: String): Maybe<List<GitHubUserRepo>> =
        gitHubStorage
            .gitHubUserRepoDao()
            .fetchUserRepos(repositoriesUrl)
            .flatMap { gitHubUserRepos ->
                if (gitHubUserRepos.isNullOrEmpty()) {
                    Maybe.empty()
                } else {
                    Maybe.just(gitHubUserRepos)
                }
            }

    override fun getUserRepositoryInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo> =
        gitHubStorage
            .gitHubUserRepoInfoDao()
            .fetchGitHubUserRepoInfo(repositoryUrl)

    override fun saveAvatarCachedPath(userId: Long, outputFileName: String): Single<String> =
        gitHubStorage
            .gitHubUserDao()
            .saveAvatarCachedPath(userId, outputFileName)
            .andThen(Single.just(outputFileName))
}
