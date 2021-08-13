package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserRepoEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity

interface UserView : MvpView {
    @AddToEndSingle
    fun showUser(user: GitHubUserEntity)

    @AddToEndSingle
    fun showRepos(gitHubUserRepos: List<GitHubUserRepoEntity>)

    @AddToEndSingle
    fun loadingLayoutIsVisible(isVisible: Boolean)

    @OneExecution
    fun showError(error: Throwable)

    @OneExecution
    fun showUserNotFound()

    @OneExecution
    fun showReposNotFound()
}
