package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val GIT_HUB_BASE_URL = "https://api.github.com"

object GitHubApiFactory {

    private val gson: Gson =
        GsonBuilder()
            .create()

    fun create(): GitHubApi =
        Retrofit.Builder()
            .baseUrl(GIT_HUB_BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitHubApi::class.java)
}
