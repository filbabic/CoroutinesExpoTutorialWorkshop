package com.filip.babic.coroutinesexpo.ui.users

import com.filip.babic.coroutinesexpo.model.UserItem
import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface UsersContract {

    interface View : BaseView {

        fun showUsers(users: List<UserItem>)
    }

    interface Presenter : BasePresenter<View>
}