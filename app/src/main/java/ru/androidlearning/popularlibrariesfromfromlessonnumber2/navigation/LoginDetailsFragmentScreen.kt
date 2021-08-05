package ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details.LoginDetailsFragment

class LoginDetailsFragmentScreen(private val userId: Long) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        LoginDetailsFragment.newInstance(userId)

}
