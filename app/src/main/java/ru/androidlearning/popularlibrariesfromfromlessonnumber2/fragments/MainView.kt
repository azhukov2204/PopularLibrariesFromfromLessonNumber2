package ru.androidlearning.popularlibrariesfromfromlessonnumber2.fragments

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

//@AddToEndSingle - есть ещё такой алиас
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView