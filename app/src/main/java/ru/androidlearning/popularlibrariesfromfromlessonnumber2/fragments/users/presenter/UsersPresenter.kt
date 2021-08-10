package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UserItemView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UsersView
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUser
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.LoginDetailsFragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class UsersPresenter(
    private val usersRepository: GitHubUsersRepository,
    private val router: Router,
    private val schedulers: WorkSchedulers
) : MvpPresenter<UsersView>() {
    private val disposables = CompositeDisposable()

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
        disposables +=
            usersRepository.getUsers()
                .subscribeOn(schedulers.threadIO())
                .observeOn(schedulers.threadMain())
                .subscribe(
                    this::showUsers,
                    viewState::showError
                )
    }

    private fun showUsers(users: List<GithubUser>) {
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
