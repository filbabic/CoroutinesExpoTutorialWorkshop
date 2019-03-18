package com.filip.babic.coroutinesexpo.ui.users

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.model.UserItem
import kotlinx.android.synthetic.main.fragment_users.*
import org.koin.android.ext.android.inject

class UsersFragment : Fragment(), UsersContract.View {

    private val presenter by inject<UsersContract.Presenter>()
    private val adapter by lazy { UsersAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        presenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    private fun initUi() {
        usersList.layoutManager = LinearLayoutManager(activity)
        usersList.adapter = adapter
    }

    override fun showUsers(users: List<UserItem>) = adapter.setData(users)
}