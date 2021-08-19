package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.GitHubUserRepoInfo
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.dao.GitHubUserDao
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.dao.GitHubUserRepoDao
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.dao.GitHubUserRepoInfoDao

@Database(exportSchema = false, entities = [GitHubUser::class, GitHubUserRepo::class, GitHubUserRepoInfo::class], version = 1)
abstract class GitHubStorage : RoomDatabase() {
    abstract fun gitHubUserDao(): GitHubUserDao
    abstract fun gitHubUserRepoDao(): GitHubUserRepoDao
    abstract fun gitHubUserRepoInfoDao(): GitHubUserRepoInfoDao
}
