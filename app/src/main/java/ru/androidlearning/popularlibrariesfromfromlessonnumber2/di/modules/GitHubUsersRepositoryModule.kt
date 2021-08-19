package ru.androidlearning.popularlibrariesfromfromlessonnumber2.di.modules

import dagger.Binds
import dagger.Module
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache.ImageToCacheSaver
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache.ImageToCacheSaverImpl
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepositoryImpl
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.GitHubUserCache
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.GitHubUserCacheImpl
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.GitHubUserCloud
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.GitHubUserCloudImpl
import javax.inject.Singleton

@Module
interface GitHubUsersRepositoryModule {

    @Singleton
    @Binds
    fun bindGitHubUserCloudDataSource(dataSource: GitHubUserCloudImpl): GitHubUserCloud

    @Singleton
    @Binds
    fun bindGitHubUserCacheDataSource(dataSource: GitHubUserCacheImpl): GitHubUserCache

    @Singleton
    @Binds
    fun bindImageToCacheSaver(imageToCacheSaver: ImageToCacheSaverImpl): ImageToCacheSaver

    @Singleton
    @Binds
    fun bindGitHubUsersRepository(repository: GitHubUsersRepositoryImpl): GitHubUsersRepository
}
