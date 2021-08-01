package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UserItemView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UsersView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUsersRepo

class UsersPresenter(val usersRepo: GithubUsersRepo, val router: Router) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя c помощью router.navigateTo
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}