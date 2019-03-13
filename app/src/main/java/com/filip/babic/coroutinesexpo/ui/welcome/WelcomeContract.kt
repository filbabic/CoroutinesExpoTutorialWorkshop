package com.filip.babic.coroutinesexpo.ui.welcome

import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface WelcomeContract {

    interface View : BaseView {

        fun startHomeScreen()
    }

    interface Presenter : BasePresenter<View> {

    }
}