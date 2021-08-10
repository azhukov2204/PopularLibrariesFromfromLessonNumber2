package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GitHubUsersRepository
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers

class LoginDetailsPresenter(
    private val gitHubUsersRepository: GitHubUsersRepository,
    private val router: Router, private val userId: Long?,
    private val schedulers: WorkSchedulers
) : MvpPresenter<LoginDetailsView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserLoginData()
    }

    private fun loadUserLoginData() {
        userId?.let { userId ->
            disposables +=
                gitHubUsersRepository.getLoginByUserId(userId)
                    .subscribeOn(schedulers.threadIO())
                    .observeOn(schedulers.threadMain())
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
