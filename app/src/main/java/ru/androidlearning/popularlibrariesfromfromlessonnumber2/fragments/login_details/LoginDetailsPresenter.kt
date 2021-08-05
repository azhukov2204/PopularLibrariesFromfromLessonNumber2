package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GitHubUsersRepository

class LoginDetailsPresenter(private val gitHubUsersRepository: GitHubUsersRepository, private val router: Router, private val userId: Long?) :
    MvpPresenter<LoginDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserLoginData()
    }

    private fun loadUserLoginData() {
        userId?.let { userId ->
            Observable.fromCallable { gitHubUsersRepository.getLoginByUserId(userId) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ login ->
                    viewState.showUser(login)
                }, {
                    it.printStackTrace()
                })
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
