package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UserItemView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UsersView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.LoginDetailsFragmentScreen

class UsersPresenter(private val usersRepository: GitHubUsersRepository, private val router: Router) : MvpPresenter<UsersView>() {

    class UsersListPresenter(private val router: Router) : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((Long) -> Unit)? = { userId ->
            router.navigateTo(LoginDetailsFragmentScreen(userId))
        }

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            view.setOnClickListener(user.userId)
        }
    }

    val usersListPresenter = UsersListPresenter(router)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        val users = usersRepository.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}