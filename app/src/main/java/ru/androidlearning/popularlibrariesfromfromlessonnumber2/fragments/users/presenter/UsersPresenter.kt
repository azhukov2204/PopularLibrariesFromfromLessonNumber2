package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
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
        loadUsersList()
    }

    private fun loadUsersList() {
        Observable.fromCallable { usersRepository.getUsers() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                it.printStackTrace()
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
