package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation.IScreens

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}
