package com.filip.babic.coroutinesexpo.ui.login

import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface LoginContract {

    interface View : BaseView {

        fun startHomeScreen()
    }

    interface Presenter : BasePresenter<View> {

        fun submit()

        fun emailUpdated(email: String)

        fun passwordUpdated(password: String)
    }
}