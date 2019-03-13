package com.filip.babic.coroutinesexpo.ui.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.common.onClick
import com.filip.babic.coroutinesexpo.common.onTextChanged
import com.filip.babic.coroutinesexpo.common.removeTextWatchers
import com.filip.babic.coroutinesexpo.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    companion object {
        fun getLaunchIntent(context: Context): Intent = Intent(context, RegisterActivity::class.java)
    }

    private val presenter: RegisterContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        registerButton.onClick(presenter::submit)
        usernameInput.onTextChanged(presenter::usernameUpdated)
        emailInput.onTextChanged(presenter::emailUpdated)
        passwordInput.onTextChanged(presenter::passwordUpdated)
    }

    override fun onDestroy() {
        listOf(usernameInput, emailInput, passwordInput).forEach { it.removeTextWatchers() }
        super.onDestroy()
    }

    override fun startHomeScreen() = startActivity(HomeActivity.getLaunchIntent(this))
}