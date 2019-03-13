package com.filip.babic.coroutinesexpo.ui.posts.post

import com.filip.babic.coroutinesexpo.ui.base.BasePresenter
import com.filip.babic.coroutinesexpo.ui.base.BaseView

interface AddPostContract {

    interface View : BaseView {

        fun onPostAdded()
    }

    interface Presenter : BasePresenter<View> {

        fun postTitleChanged(title: String)

        fun postDescriptionChanged(description: String)

        fun onFeaturePostChanged(isChecked: Boolean)

        fun submit()
    }
}