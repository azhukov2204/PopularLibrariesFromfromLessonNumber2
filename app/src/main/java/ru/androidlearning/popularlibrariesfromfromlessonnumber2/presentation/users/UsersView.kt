package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity

@AddToEndSingle
interface UsersView : MvpView {
    fun showUsers(users: List<GithubUserEntity>)
    fun showError(error: Throwable)
}
