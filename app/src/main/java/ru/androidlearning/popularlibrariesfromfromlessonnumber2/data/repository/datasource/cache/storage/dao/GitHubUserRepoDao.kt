package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo

@Dao
interface GitHubUserRepoDao {
    @Query("SELECT * FROM github_user_repo WHERE repos_url = :reposUrl")
    fun fetchUserRepos(reposUrl: String): Maybe<List<GitHubUserRepo>>

    @Insert(onConflict = IGNORE)
    fun retain(userRepositories: List<GitHubUserRepo>): Completable
}