package ru.androidlearning.popularlibrariesfromfromlessonnumber2.di.modules

import dagger.Module
import dagger.Provides
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulers
import ru.androidlearning.popularlibrariesfromfromlessonnumber2.scheduler.WorkSchedulersImpl

@Module
class WorkSchedulersModule {

    @Provides
    fun provideWorkSchedulers(): WorkSchedulers = WorkSchedulersImpl()
}
