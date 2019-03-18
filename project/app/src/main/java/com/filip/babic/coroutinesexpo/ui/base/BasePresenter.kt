package com.filip.babic.coroutinesexpo.ui.base

interface BasePresenter<in View : BaseView> {

    fun setView(view: View)

    fun start()

    fun stop()
}