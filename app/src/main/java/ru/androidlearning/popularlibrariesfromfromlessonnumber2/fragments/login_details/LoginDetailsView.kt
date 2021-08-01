package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface LoginDetailsView: MvpView {
    fun renderData(login: String)
}