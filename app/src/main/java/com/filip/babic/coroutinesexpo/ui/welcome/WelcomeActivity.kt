package com.filip.babic.coroutinesexpo.ui.welcome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.ui.home.HomeActivity
import com.filip.babic.coroutinesexpo.ui.login.LoginActivity
import com.filip.babic.coroutinesexpo.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_welcome.*
import org.koin.android.ext.android.inject

class WelcomeActivity : AppCompatActivity(), WelcomeContract.View {

    private val presenter by inject<WelcomeContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        presenter.setView(this)
        initUi()

        presenter.start()
    }

    private fun initUi() {
        login.setOnClickListener { startActivity(LoginActivity.getLaunchIntent(this)) }
        register.setOnClickListener { startActivity(RegisterActivity.getLaunchIntent(this)) }
    }

    override fun startHomeScreen() = startActivity(HomeActivity.getLaunchIntent(this))
}