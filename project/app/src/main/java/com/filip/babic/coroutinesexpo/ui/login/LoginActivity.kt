package com.filip.babic.coroutinesexpo.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.common.onClick
import com.filip.babic.coroutinesexpo.common.onTextChanged
import com.filip.babic.coroutinesexpo.common.removeTextWatchers
import com.filip.babic.coroutinesexpo.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    companion object {
        fun getLaunchIntent(context: Context): Intent = Intent(context, LoginActivity::class.java)
    }

    private val presenter: LoginContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        loginButton.onClick(presenter::submit)
        emailInput.onTextChanged(presenter::emailUpdated)
        passwordInput.onTextChanged(presenter::passwordUpdated)
    }

    override fun onDestroy() {
        listOf(emailInput, passwordInput).forEach { it.removeTextWatchers() }
        super.onDestroy()
    }

    override fun startHomeScreen() = startActivity(HomeActivity.getLaunchIntent(this))
}