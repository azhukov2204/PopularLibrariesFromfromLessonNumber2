package ru.androidlearning.popularlibrariesfromfromlessonnumber2.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage.GitHubStorage
import javax.inject.Singleton

@Module
class GitHubStorageModule {

    @Singleton
    @Provides
    fun provideGitHubStorage(context: Context): GitHubStorage = Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
        .build()
}
