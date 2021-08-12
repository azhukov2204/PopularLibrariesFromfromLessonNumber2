package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity

@SingleState
interface UserView: MvpView {
    fun showUser(user: GithubUserEntity)
    fun showError(error: Throwable)
    fun showUserNotFound()
}
