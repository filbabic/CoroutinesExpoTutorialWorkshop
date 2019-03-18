package com.filip.babic.coroutinesexpo.ui.users

import com.filip.babic.coroutinesexpo.interaction.StorageInteractor
import com.filip.babic.coroutinesexpo.ui.base.BasePresenterImpl

class UsersPresenter(
    private val storageInteractor: StorageInteractor
) : BasePresenterImpl<UsersContract.View>(), UsersContract.Presenter {

    override fun start() {
        super.start()

        storageInteractor.getUsers { users -> viewAction { showUsers(users) } }
    }
}