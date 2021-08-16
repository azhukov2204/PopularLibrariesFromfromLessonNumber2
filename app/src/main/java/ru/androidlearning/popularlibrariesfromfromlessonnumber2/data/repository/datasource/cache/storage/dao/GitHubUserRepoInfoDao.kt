package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo

@Dao
interface GitHubUserRepoInfoDao {
    @Query("SELECT * FROM github_user_repo_info WHERE repo_url = :repositoryUrl")
    fun fetchGitHubUserRepoInfo(repositoryUrl: String): Maybe<GitHubUserRepoInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun retain(gitHubUserRepoInfo: GitHubUserRepoInfo): Completable
}