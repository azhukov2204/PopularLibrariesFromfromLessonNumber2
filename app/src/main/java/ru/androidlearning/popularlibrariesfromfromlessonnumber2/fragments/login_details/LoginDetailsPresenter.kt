package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.model.GithubUsersRepo

class LoginDetailsPresenter(private val githubUsersRepo: GithubUsersRepo, private val router: Router) : MvpPresenter<LoginDetailsView>() {
    private var position: Int? = null

    fun setPosition(position: Int?) {
        this.position = position
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserLoginData()
    }

    private fun loadUserLoginData() {
        position?.let {
            val login = githubUsersRepo.getUsers()[it].login
            viewState.renderData(login)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
