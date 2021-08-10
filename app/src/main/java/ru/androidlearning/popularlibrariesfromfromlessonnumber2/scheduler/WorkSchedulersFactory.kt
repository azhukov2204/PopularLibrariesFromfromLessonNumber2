package ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler

object WorkSchedulersFactory {
    fun create(): WorkSchedulers = WorkSchedulersImpl()
}
