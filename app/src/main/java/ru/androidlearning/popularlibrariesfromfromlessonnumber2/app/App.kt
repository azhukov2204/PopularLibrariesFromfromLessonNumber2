package ru.androidlearning.popularlibrariesfromfromlessonnumber2.app

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.di.DaggerApplicationComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext).apply {
                val cicerone: Cicerone<Router> = Cicerone.create()
                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .build()
}
