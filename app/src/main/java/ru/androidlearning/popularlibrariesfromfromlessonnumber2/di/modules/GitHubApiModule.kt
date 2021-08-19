package ru.androidlearning.popularlibrariesfromfromlessonnumber2.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cloud.api.GitHubApi
import javax.inject.Named
import javax.inject.Singleton

@Module
class GitHubApiModule {

    @Named("git_hub_base_url")
    @Provides
    fun provideGitHubBaseUrl(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun provideGitHubApi(@Named("git_hub_base_url") baseUrl: String): GitHubApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .create()
}
