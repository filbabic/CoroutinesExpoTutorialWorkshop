package com.filip.babic.coroutinesexpo.ui.base

abstract class BasePresenterImpl<View : BaseView> : BasePresenter<View> {

    private lateinit var view: View

    override fun setView(view: View) {
        this.view = view
    }

    protected fun viewAction(action: View.() -> Unit) {
        view.action()
    }

    override fun start() {
        // start up the presenter
    }

    override fun stop() {
        // clean up
    }
}