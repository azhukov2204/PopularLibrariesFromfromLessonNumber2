package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.datasource.cache.storage

import android.content.Context
import androidx.room.Room

object GitHubStorageFactory {
    fun create(context: Context): GitHubStorage =
        Room.databaseBuilder(context, GitHubStorage::class.java, "github.db")
            .build()
}