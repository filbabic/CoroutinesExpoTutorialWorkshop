package com.filip.babic.coroutinesexpo.ui.posts.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.common.onClick
import com.filip.babic.coroutinesexpo.common.onTextChanged
import kotlinx.android.synthetic.main.activity_add_post.*
import org.koin.android.ext.android.inject

class AddPostActivity : AppCompatActivity(), AddPostContract.View {

    private val presenter by inject<AddPostContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        initUi()
        presenter.setView(this)
    }

    private fun initUi() {
        titleInput.onTextChanged(presenter::postTitleChanged)
        descriptionInput.onTextChanged(presenter::postDescriptionChanged)
        featurePostCheckbox.setOnCheckedChangeListener { _, isChecked -> presenter.onFeaturePostChanged(isChecked) }
        addPostButton.onClick(presenter::submit)
    }

    override fun onPostAdded() {
        finish()
    }
}