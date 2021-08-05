package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GitHubUsersRepository

class LoginDetailsPresenter(private val gitHubUsersRepository: GitHubUsersRepository, private val router: Router, private val userId: Long?) : MvpPresenter<LoginDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserLoginData()
    }

    private fun loadUserLoginData() {
        userId?.let { userId ->
            gitHubUsersRepository.getLoginByUserId(userId)?.let { login ->
                viewState.showUser(login)
            }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
