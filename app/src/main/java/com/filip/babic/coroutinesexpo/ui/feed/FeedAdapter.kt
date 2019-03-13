package com.filip.babic.coroutinesexpo.ui.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filip.babic.coroutinesexpo.R
import com.filip.babic.coroutinesexpo.model.Post
import kotlinx.android.synthetic.main.item_feed.view.*
import java.text.SimpleDateFormat
import java.util.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private val items = mutableListOf<Post>()

    private val formatter by lazy { SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()) }

    fun setData(data: List<Post>) {
        this.items.clear()
        this.items.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: FeedViewHolder, position: Int) {
        viewHolder.showData(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false))
    }

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showData(item: Post) = with(itemView) {
            itemTitle.text = item.title
            itemText.text = item.text
            itemTimestamp.text =formatter.format(item.timeAdded)
        }
    }
}