package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser

@Dao
abstract class GitHubUserDao {
    @Query("SELECT * FROM github_users")
    abstract fun fetchUsers(): Maybe<List<GitHubUser>>

    @Query("SELECT * FROM github_users WHERE login LIKE :login LIMIT 1")
    abstract fun fetchUserByLogin(login: String): Maybe<GitHubUser>

    @Insert(onConflict = REPLACE)
    abstract fun retain(users: List<GitHubUser>): Completable

    @Query("UPDATE github_users SET login = :login, avatar_url = :avatarUrl, repos_url = :reposUrl WHERE id = :id")
    abstract fun updateUser(id: Long, login: String, avatarUrl: String?, reposUrl: String)

    @Transaction
    open fun retainUser(user: GitHubUser) {
        updateUser(user.userId, user.login, user.avatarUrl, user.reposUrl)
    }

    open fun retain(user: GitHubUser): Completable =
        Completable.fromAction {
            retainUser(user)
        }

    @Query("UPDATE github_users SET avatar_local_path = :outputFileName WHERE id = :userId")
    abstract fun saveAvatarCachedPath(userId: Long, outputFileName: String): Completable
}
