package ru.androidlearning.popularlibrariesfromfromlessonnumber2.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.repo_info.RepoInfoFragment

class RepoInfoFragmentScreen(private val repositoryUrl: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        RepoInfoFragment.newInstance(repositoryUrl)
}
