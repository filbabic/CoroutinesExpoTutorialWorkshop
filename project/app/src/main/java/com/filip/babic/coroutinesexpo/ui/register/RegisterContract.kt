package com.filip.babic.coroutinesexpo.ui.register

import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface RegisterContract {

    interface View : BaseView {

        fun startHomeScreen()
    }

    interface Presenter : BasePresenter<View> {

        fun usernameUpdated(username: String)

        fun emailUpdated(email: String)

        fun passwordUpdated(password: String)

        fun submit()
    }
}