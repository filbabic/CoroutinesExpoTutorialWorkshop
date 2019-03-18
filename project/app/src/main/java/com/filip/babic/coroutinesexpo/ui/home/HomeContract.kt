package com.filip.babic.coroutinesexpo.ui.home

import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface HomeContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }
}