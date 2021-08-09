package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface LoginDetailsView: MvpView {
    fun showUser(login: String)
    fun showError(error: Throwable)
    fun showUserNotFound()
}
