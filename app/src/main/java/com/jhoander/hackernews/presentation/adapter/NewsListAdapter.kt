package com.jhoander.hackernews.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhoander.hackernews.R
import com.jhoander.hackernews.domain.model.Hit
import com.jhoander.hackernews.utils.extension.inflate
import com.jhoander.hackernews.utils.extension.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_news_list.view.*


class NewsListAdapter constructor( val listener : (String) -> Unit):
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private lateinit var hits: List<Hit>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListAdapter.ViewHolder {
        return ViewHolder(
            parent.inflate(R.layout.item_news_list)
        )
    }

    override fun getItemCount(): Int {
        return hits.size
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ViewHolder, position: Int) {
        holder.itemView.newsTitle.text = hits[position].title
        holder.itemView.newsTitle.text = hits[position].story_title
        holder.itemView.newsAuthor.text = hits[position].author
        holder.itemView.newsCreatedAt.text = hits[position].created_at
    }

    fun setList(hits: List<Hit>) {
        this.hits = hits
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setSafeOnClickListener {
                listener(hits[adapterPosition].story_url)
            }
        }
    }
}