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

class FeedAdapter(
    private val isSelectionEnabled: Boolean,
    private val onItemsSelected: (Boolean) -> Unit = {}
) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private val items = mutableListOf<Post>()
    private val selectedPosts = mutableListOf<String>()

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

    private fun onItemTapped(postId: String) {
        if (selectedPosts.isNotEmpty()) {
            handlePostSelection(postId)
        }
    }

    private fun onItemLongTapped(postId: String) {
        handlePostSelection(postId)
    }

    private fun handlePostSelection(postId: String) {
        val exists = postId in selectedPosts

        if (exists) {
            selectedPosts.remove(postId)
        } else {
            selectedPosts.add(postId)
        }

        notifyDataSetChanged()
        onItemsSelected(selectedPosts.isNotEmpty())
    }

    fun getSelectedPosts(): List<String> = selectedPosts

    fun removePosts(removedPosts: List<String>) {
        items.removeAll { it.id in removedPosts }
        notifyDataSetChanged()
    }

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showData(item: Post) = with(itemView) {
            itemTitle.text = item.title
            itemText.text = item.text
            itemTimestamp.text = formatter.format(item.timeAdded)

            val backgroundColor = resources.getColor(
                if (item.id in selectedPosts && isSelectionEnabled) {
                    R.color.selected_background
                } else {
                    R.color.unselected_background
                }
            )

            feedItemRoot.setCardBackgroundColor(backgroundColor)

            if (isSelectionEnabled) {
                setOnLongClickListener {
                    item.id?.run(::onItemLongTapped)
                    true
                }

                setOnClickListener { item.id?.run(::onItemTapped) }
            } else {
                setOnLongClickListener(null)
                setOnClickListener(null)
            }
        }
    }
}