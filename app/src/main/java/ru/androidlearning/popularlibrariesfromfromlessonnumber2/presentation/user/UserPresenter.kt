package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.data.user.repository.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.GithubUserEntity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class UserPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val router: Router,
    private val userLogin: String?,
    private val schedulers: WorkSchedulers
) : MvpPresenter<UserView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserLoginData()
    }

    private fun loadUserLoginData() {
        userLogin?.let { login ->
            disposables +=
                gitHubUsersRepository.getUserByLogin(login)
                    .map(GithubUserEntity.Mapper::map)
                    .observeOn(schedulers.threadMain())
                    .subscribeOn(schedulers.threadIO())
                    .subscribe(
                        viewState::showUser,
                        viewState::showError,
                        viewState::showUserNotFound
                    )
        }
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
