package ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.UsersFragmentScreen

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersFragmentScreen)
    }

    fun backClicked() {
        router.exit()
    }
}
