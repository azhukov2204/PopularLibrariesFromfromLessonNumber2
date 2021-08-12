package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.LoginDetailsFragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class UsersPresenter(
    private val usersRepository: GitHubUsersRepository,
    private val router: Router,
    private val schedulers: WorkSchedulers
) : MvpPresenter<UsersView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUsersList()
    }

    private fun loadUsersList() {
        disposables +=
            usersRepository.getUsers()
                .map { users -> users.map { GithubUserEntity.Mapper.map(it) } }
                .observeOn(schedulers.threadMain())
                .subscribeOn(schedulers.threadIO())
                .subscribe(
                    viewState::showUsers,
                    viewState::showError
                )
    }

    fun displayUser(user: GithubUserEntity) {
        router.navigateTo(LoginDetailsFragmentScreen(user.login))
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
