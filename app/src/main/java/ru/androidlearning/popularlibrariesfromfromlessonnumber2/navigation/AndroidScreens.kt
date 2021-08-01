package ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.login_details.LoginDetailsFragment
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments.users.view.UsersFragment

class AndroidScreens:IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun loginDetails(position: Int) = FragmentScreen { LoginDetailsFragment.newInstance(position) }
}
