package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GitHubUserEntity

@AddToEndSingle
interface UsersView : MvpView {
    fun showUsers(users: List<GitHubUserEntity>)
    fun showError(error: Throwable)
}
