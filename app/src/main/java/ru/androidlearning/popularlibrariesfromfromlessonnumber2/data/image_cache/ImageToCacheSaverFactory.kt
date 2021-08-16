package ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.image_cache

import android.content.Context
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.repository.GitHubUsersRepositoryFactory

object ImageToCacheSaverFactory {
    fun create(context: Context): ImageToCacheSaver = ImageToCacheSaverImpl(
        gitHubUsersRepository = GitHubUsersRepositoryFactory.create(context),
        context = context
    )
}