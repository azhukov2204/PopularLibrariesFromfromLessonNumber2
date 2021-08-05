package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun setOnClickListener(usrId: Long)
}

interface IItemView {
    var pos: Int
}
