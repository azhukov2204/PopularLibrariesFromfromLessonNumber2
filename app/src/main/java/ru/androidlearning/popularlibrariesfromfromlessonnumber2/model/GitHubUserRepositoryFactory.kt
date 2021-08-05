package ru.androidlearning.popularlibrariesfromfromlessonnumber2.model

/**
 * Пока нет DI на основе Dagger2 мы решаем проблему
 * по старинке используя фабрики.
 */
object GitHubUsersRepositoryFactory {
    fun create(): GitHubUsersRepository = GitHubUsersRepositoryImpl()
}
