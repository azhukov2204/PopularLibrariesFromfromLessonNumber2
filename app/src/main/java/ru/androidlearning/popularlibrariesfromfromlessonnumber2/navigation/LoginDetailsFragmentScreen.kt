package ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user.UserFragment

class LoginDetailsFragmentScreen(private val login: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        UserFragment.newInstance(login)

}
