package ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

class WorkSchedulersImpl : WorkSchedulers {
    override fun threadMain(): Scheduler = AndroidSchedulers.mainThread()
    override fun threadIO(): Scheduler = io.reactivex.rxjava3.schedulers.Schedulers.io()
}
