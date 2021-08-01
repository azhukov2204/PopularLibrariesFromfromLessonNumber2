package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.presenter

import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.IItemView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
