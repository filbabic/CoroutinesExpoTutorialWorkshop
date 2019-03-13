package com.filip.babic.coroutinesexpo.ui.profile

import com.filip.babic.coroutinesexpo.model.UserProfile
import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface ProfileContract {

    interface View : BaseView {

        fun render(userProfile: UserProfile)
    }

    interface Presenter : BasePresenter<View> {

    }
}