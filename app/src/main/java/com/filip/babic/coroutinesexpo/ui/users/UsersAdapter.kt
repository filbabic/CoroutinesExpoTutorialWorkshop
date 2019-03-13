package com.filip.babic.coroutinesexpo.ui.users

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.model.UserItem
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private val items = mutableListOf<UserItem>()

    fun setData(data: List<UserItem>) {
        this.items.clear()
        this.items.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: UsersViewHolder, position: Int) {
        viewHolder.showData(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showData(item: UserItem) = with(itemView) {
            val (user, postsCount) = item

            numberOfPosts.text = resources.getString(R.string.number_of_posts, postsCount)
            username.text = user.username
            email.text = user.email
        }
    }
}