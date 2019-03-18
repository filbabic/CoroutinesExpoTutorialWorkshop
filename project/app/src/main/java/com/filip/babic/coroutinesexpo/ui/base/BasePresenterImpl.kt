package com.filip.babic.coroutinesexpo.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class BasePresenterImpl<View : BaseView> : BasePresenter<View>, CoroutineScope {

    private lateinit var view: View

    protected var parentJob = Job()

    override fun setView(view: View) {
        this.view = view
    }

    protected fun viewAction(action: View.() -> Unit) {
        launch(Dispatchers.Main) { view.action() }
    }

    override fun start() {
        parentJob = Job()
        // start up the presenter
    }

    override fun stop() {
        // clean up
        parentJob.cancel()
    }

    fun runInBackground(action: suspend (CoroutineScope) -> Unit) {
        launch { action(this) }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + parentJob
}