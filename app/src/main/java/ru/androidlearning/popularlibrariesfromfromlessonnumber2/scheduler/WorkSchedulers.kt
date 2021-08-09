package ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface WorkSchedulers {
    fun threadIO(): Scheduler
    fun threadMain(): Scheduler
}
