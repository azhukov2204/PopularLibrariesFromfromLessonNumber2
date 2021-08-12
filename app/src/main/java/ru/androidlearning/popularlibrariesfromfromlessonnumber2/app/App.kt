package ru.androidlearning.popularlibrariesfromfromlessonnumber2.app

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    /**
     * Пока нет DI на основе Dagger2 мы решаем проблему
     * по старинке используя фабрики.
     */
    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}