package ru.androidlearning.popularlibrariesfromfromlessonnumber2.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.main_activity.MainActivity
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.repo_info.RepoInfoFragment
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.user.UserFragment
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.presentation.users.UsersFragment

@Module
interface FragmentsModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindRepoInfoFragment(): RepoInfoFragment

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment
}
